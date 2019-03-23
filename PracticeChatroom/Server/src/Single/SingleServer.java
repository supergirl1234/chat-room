package Single;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author:Fanleilei
 * Created:2019/3/22 0022
 */
public class SingleServer {

    public static void main(String[] args) {

        try {
            ServerSocket socket=new ServerSocket(6666);
            System.out.println("服务器正在等待中....,其端口地址为："+socket.getLocalPort());
            Socket clientSocket=socket.accept();
            System.out.println("有客户端连接，客户端地址："+clientSocket.getLocalSocketAddress());


                //接收客户端发来的数据
                InputStream inputStream=clientSocket.getInputStream();
                Scanner scanner=new Scanner(inputStream);
                String data=scanner.nextLine();
                System.out.println("客户端发来的信息为："+data);


                //服务器发送数据给客户端

                OutputStreamWriter out=new OutputStreamWriter(clientSocket.getOutputStream());
                out.write("你好，客户端");
                out.flush();

                //要记住关闭服务器端口
                socket.close();
            System.out.println("服务器端关闭");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
