package io;

import com.sun.org.apache.xml.internal.serializer.EmptySerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Yale.Wei
 * @date 2018/12/1 16:05
 */
public class EchoClient {


    public static void main(String[] args) {
        Socket client = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            client = new Socket("127.0.0.1",8080);
            out = new PrintWriter(client.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            System.out.println("Connected to the server ......");
            System.out.println("You can input [QUIT] to exit if you want:");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = stdin.readLine()) != null){
                out.println(userInput);
                System.out.println(in.readLine());

                if (userInput.equalsIgnoreCase("Quit")){
                    System.out.println("Shut down the client ......");
                    out.close();
                    in.close();
                    stdin.close();
                    client.close();
                    System.exit(1);
                }
                System.out.println("You can input [QUIT] to exit if you want:");

            }

        } catch (UnknownHostException e){
            System.err.println("Unkonw host address :" +client.getInetAddress().getHostAddress());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Couldn't get I/O for the connection to :" +client.getInetAddress().getHostAddress());
            System.exit(1);
        }
    }
}
