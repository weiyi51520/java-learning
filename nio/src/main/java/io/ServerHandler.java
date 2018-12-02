package io;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author Yale.Wei
 * @date 2018/12/1 16:43
 */
public class ServerHandler implements Runnable{
    Socket client = null;
    int clientNo = 0;

    ServerHandler(Socket client, int clientNo) {
        if (client!=null){
            this.client = client;
            this.clientNo = clientNo;
            System.out.println("Created thread connect to client [" +clientNo+"]");
        }
    }

    @Override
    public void run() {
        PrintStream os = null;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            os = new PrintStream(client.getOutputStream());
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                if (inputLine.equalsIgnoreCase("QUIT")){
                    System.out.println("Shut down the link with client [" + clientNo + "]");
                    os.close();
                    in.close();
                    client.close();
                    break;
                }else {
                    System.out.println("Message from clinet [" + clientNo + "] : [" + inputLine + "]");
                    os.println("Response from server: " + inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Stream closed");
        }

    }
}
