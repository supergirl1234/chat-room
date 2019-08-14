package com.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        //服务器正在等待时，一个客户端实例化一个 Socket 对象，指定服务器名称和端口号来请求连接。
        String ServerName = "127.0.0.1";
        int ServerPort = 1234;
        Socket socket = null;
        try {
            socket = new Socket(ServerName, ServerPort);/*客户端实例化一个socket*/

            System.out.println("连接到服务器端" + socket.getLocalSocketAddress());
            /*针对于客户端来说，服务器发送来的数据属于输入流；客户端要发送给服务器的数据属于输出流*/



            /*向服务器端发送数据*/
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write("hello,认识一下哈\n");
           writer.flush();


            /*接收服务器端传来的信息*/
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            System.out.println("服务器端发送的数据：" + scanner.nextLine());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    System.out.println("客户端关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
