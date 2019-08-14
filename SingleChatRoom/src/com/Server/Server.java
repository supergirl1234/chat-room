package com.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);//服务器实例化一个 ServerSocket 对象
            System.out.println("服务器启动" + serverSocket.getLocalSocketAddress());

            //服务器调用 ServerSocket 类的 accept() 方法，该方法将一直等待，直到客户端连接到服务器上给定的端口
            Socket ClientSocket = serverSocket.accept();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (ClientSocket != null) {
                            System.out.println("客户端"+ClientSocket.getInetAddress() + "连接到服务器端");
                            /*针对于服务器端来说，客户端的数据，接收，属于输入流；而服务器要发送的是数据属于输出流*/

                            /*服务器端接收来自客户端的数据*/

                            InputStream in = ClientSocket.getInputStream();
                            Scanner scanner = new Scanner(in);
                            System.out.println("接受到的客户端的数据为：" + scanner.nextLine());

                            /*服务器端发送数据*/
                            OutputStream out = ClientSocket.getOutputStream();
                            OutputStreamWriter writer = new OutputStreamWriter(out);
                            writer.write("hello,make friends!\n");
                            writer.flush();



                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}