package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Yale.Wei
 * @date 2018/12/4 13:44
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        //Open a SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8080));

        //Reading data from a SocketChannel into Buffer
        ByteBuffer buffer = ByteBuffer.allocate(48);
        socketChannel.read(buffer);

        //Writing data from Buffer into SocketChannel
        buffer.flip();
        while (buffer.hasRemaining()){
            socketChannel.write(buffer);
        }

        //Configure the SocketChannel be non-blocking mode,the method may return before a connection is established.
        //Then call the finishConnect() method to determine whether connection is established.
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost",8080));
        if (socketChannel.finishConnect()) {
            //TODO
        }

        //Closing a SocketChannel
        socketChannel.close();

    }
}
