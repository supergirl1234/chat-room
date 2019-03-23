package Mul;

import java.io.IOException;
import java.net.Socket;

/**
 * Author:Fanleilei
 * Created:2019/3/22 0022
 */
public class MulClient {


    public static void main(String[] args) {
        int port=6666;
        String address="127.0.0.1";
        try {
            Socket socket=new Socket(address,port);
            //发送信息给服务器

            new  WriteMessagetoServer(socket).start();
            //从服务器接收信息
            new ReadMessagefromServer(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
