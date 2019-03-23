package Mul;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author:Fanleilei
 * Created:2019/3/22 0022
 */
public class ReadMessagefromServer extends Thread{

    private final Socket client;

    public ReadMessagefromServer(Socket client) {
        this.client=  client;
    }

    @Override
    public void run() {
        try {
            InputStreamReader in=new InputStreamReader(client.getInputStream());
            Scanner scanner=new Scanner(in);
            while(true){
                String data=scanner.nextLine();
                System.out.println("来自服务器的消息："+data);
                if(data.equals("bye")){
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
