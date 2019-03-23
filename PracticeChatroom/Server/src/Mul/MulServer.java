package Mul;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

/**
 * Author:Fanleilei
 * Created:2019/3/22 0022
 */
public class MulServer {

    public static void main(String[] args) {

        try {
            ServerSocket socket=new ServerSocket(6666);
            System.out.println("服务器端等待中...."+socket.getLocalSocketAddress());

            //线程池
           ExecutorService executorService= Executors.newFixedThreadPool(25);
           while(true) {
               Socket client=socket.accept();
               System.out.println("有客户端连接到该服务器上"+client.getLocalSocketAddress());
               HandlerClient p = new HandlerClient(client);
               executorService.execute(p);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
