package Mul;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:Fanleilei
 * Created:2019/3/22 0022
 */
public class HandlerClient implements Runnable {

    private static Map<String,Socket> Client_map=new ConcurrentHashMap<>();
    private final Socket client;
    public HandlerClient(Socket client) {
        this.client=client;
    }

    //注册：register：name
    //私聊：privateChat：name：message
    //群聊：groupChat：message
    //退出：bye


    @Override
    public void run() {

            try {

                    //接收某个客户端发来的信息
                    InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
                    Scanner scanner = new Scanner(inputStreamReader);
                while (true) {
                    String data = scanner.nextLine();
                    //对发送过来的信息进行分析

                    if (data.startsWith("register")) {
                        String username = data.split(":")[1];
                        regisetr(username);
                        continue;

                    }
                    if (data.startsWith("privateChat")) {
                        String username = data.split(":")[1];
                        String mess = data.split(":")[2];
                        privateChat(username, mess);
                        continue;
                    }
                    if (data.startsWith("groupChat")) {
                        String mess = data.split(":")[1];
                        groupChat(mess);
                        continue;
                    }
                    if (data.startsWith("bye")) {
                        bye();
                        continue;

                    }
                }
               } catch(IOException e){
                    e.printStackTrace();
            }


    }
    //注册
    public void regisetr(String username){

        Client_map.put(username,this.client);
        System.out.println("用户"+username+"注册成功");
        PrintSocket();


    }
    //私聊
    public  void privateChat(String name,String message) {
        //先找到要私聊的对象

        Socket target=Client_map.get(name);
        if(client==null){
            SendMessage(target,"没有这个人",false);

        }else{

            SendMessage(target, message,true);
        }


    }
    //群聊
    public void groupChat(String message){

        //服务器除了不给那个发信息的发messgae，其他人都要收到message；
        for (Map.Entry< String,Socket> p : Client_map.entrySet()) {

            if(p.getValue().equals(this.client)){
                continue;
            }
            SendMessage(p.getValue(),message,true);
        }

    }
    //退出
    public void bye(){
        for(Map.Entry<String,Socket>  p:Client_map.entrySet()){
            if(p.getValue().equals(this.client)){

                Client_map.remove(p.getKey());

            }
        }
        PrintSocket();

    }

    //发送信息给客户端
    //给谁发送信息
    public void SendMessage(Socket target,String message,boolean pre){

        try {
            OutputStreamWriter out=new OutputStreamWriter(target.getOutputStream());
            if(pre==true){
                String name=this.currentName();
                out.write("<"+name+"说：>"+message+"\n");
                out.flush();
            }
            else{
                out.write(message+"\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //打印在线人数
    public  void PrintSocket(){

        System.out.println("当前在线人数为："+Client_map.size());
        for(Map.Entry< String,Socket> p : Client_map.entrySet()){

            System.out.println(p.getKey());
        }
    }

    //获取当前对象
    public String currentName(){
        for(Map.Entry< String,Socket> p : Client_map.entrySet()){

            if (p.getValue().equals(this.client)) {
                return  p.getKey();
            }
        }
        return null;

    }

}
