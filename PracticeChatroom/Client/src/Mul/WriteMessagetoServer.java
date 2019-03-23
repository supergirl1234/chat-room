package Mul;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author:Fanleilei
 * Created:2019/3/22 0022
 */
public class WriteMessagetoServer extends Thread{

    private Socket client;
    public WriteMessagetoServer(Socket client) {
        this.client=client;
    }

    @Override
    public void run() {
        try {
            OutputStreamWriter out=new OutputStreamWriter(client.getOutputStream());
            Scanner  scanner=new Scanner(System.in);
            while(true){
                String data=scanner.nextLine();
                out.write(data+"\n");
                out.flush();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
