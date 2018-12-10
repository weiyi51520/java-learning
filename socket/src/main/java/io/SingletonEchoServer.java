package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yale.Wei
 * @date 2018/12/1 15:36
 * 单线程的Socket Server
 */
public class SingletonEchoServer {
    private int port;

    public SingletonEchoServer(int port) {
        this.port = port;
    }

    public void startServer() {
        ServerSocket server = null;
        int i = 0;
        System.out.println("Server is listening on port : [" + port + "]");
        try {
            server = new ServerSocket(port);
            while (true) {
                Socket clientRequest = server.accept();
                hanldeRequest(clientRequest,i++);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void hanldeRequest(Socket client, int clientNo) {
        PrintStream os = null;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            os = new PrintStream(client.getOutputStream());
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("Quit")) {
                    System.out.println("Shut down the link with client [" + clientNo + "]");
                    os.close();
                    in.close();
                    client.close();
                    break;
                } else {
                    System.out.println("Message from clinet [" + clientNo + "] : [" + inputLine + "]");
                    os.println("Response from server: " + inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Stream closed");
        }
    }

    public static void main(String[] args) {
        new SingletonEchoServer(8080).startServer();
    }
}

