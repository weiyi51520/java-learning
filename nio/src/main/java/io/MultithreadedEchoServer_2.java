package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yale.Wei
 * @date 2018/12/1 16:55
 */
public class MultithreadedEchoServer_2 {
    private int port;

    public MultithreadedEchoServer_2(int port) {
        this.port = port;
    }

    public void startServer(){
        ServerSocket echoServer = null;
        ExecutorService pool = Executors.newFixedThreadPool(5);
        int i = 0;
        System.out.println("Server is listening on port : [" + port + "]");
        try {
            echoServer = new ServerSocket(8080);
            while (true){
                Socket clientRequest = echoServer.accept();
                pool.execute(new ServerHandler(clientRequest,i++));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new MultithreadedEchoServer_2(8080).startServer();
    }
}
