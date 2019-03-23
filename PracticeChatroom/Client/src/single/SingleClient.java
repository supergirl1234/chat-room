package single;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author:Fanleilei
 * Created:2019/3/22 0022
 */
public class SingleClient {

    public static void main(String[] args) {
        try {
            Socket socket=new Socket("127.0.0.1",6666);

            System.out.println("客户端已就绪...其端口地址为："+socket.getLocalSocketAddress());
           //发送数据
            OutputStreamWriter out=new OutputStreamWriter(socket.getOutputStream());
            out.write("你好，服务器\n");
            out.flush();
            //接收数据
            InputStream inputStream=socket.getInputStream();
            Scanner scanner=new Scanner(inputStream);
            String data=scanner.nextLine();
            System.out.println("服务器端发送的数据为："+data);


            //也是要记住关闭客户端
            socket.close();
            System.out.println("客户端关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
