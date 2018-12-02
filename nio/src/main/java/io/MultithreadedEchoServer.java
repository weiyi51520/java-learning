package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yale.Wei
 * @date 2018/12/1 16:55
 */
public class MultithreadedEchoServer {
    private int port;

    public MultithreadedEchoServer(int port) {
        this.port = port;
    }

    public void startServer(){
        ServerSocket echoServer = null;
        int i = 0;
        System.out.println("Server is listening on port : [" + port + "]");
        try {
            echoServer = new ServerSocket(8080);
            while (true){
                Socket clientRequest = echoServer.accept();
                new Thread(new ServerHandler(clientRequest,i++)).start();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new MultithreadedEchoServer(8080).startServer();
    }
}
