package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Yale.Wei
 * @date 2018/12/4 14:23
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) throws IOException {
        //Opening a ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //Binds the {@code ServerSocket} to a specific address (IP address and port number).
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while (true){
            //Listening for Incoming Connections
            //The accept() method blocks until an incoming connection arrives,and returns a SocketChannel.
            SocketChannel socketChannel = serverSocketChannel.accept();
            //TODO ...

            //In non-blocking mode the accept() method returns immediately,
            // if no incoming connection had arrived,you will have to check if the returned channel is null.
            serverSocketChannel.configureBlocking(false);
            if (socketChannel!=null){
                //TODO ...
            }

            //Closing a ServerSocketChannel
            serverSocketChannel.close();

        }


    }
}
