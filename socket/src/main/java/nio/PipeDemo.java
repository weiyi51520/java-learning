package nio;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author Yale.Wei
 * @date 2018/12/4 15:06
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        //Creating a Pipe.
        Pipe pipe = Pipe.open();

        //Writing to a Pipe.
        Pipe.SinkChannel sinkChannel = pipe.sink();//To write to a pipe you need to access the sink channel.

        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.put(new byte[]{1,2,3,4,5});
        buffer.flip();

        while (buffer.hasRemaining()){
            sinkChannel.write(buffer); //Wiriting data from buffer into pipe.sinkChannel
        }

        //Reading from a Pipe
        Pipe.SourceChannel sourceChannel = pipe.source(); //To read from a pipe you need to access the channel.
        ByteBuffer buffer2 = ByteBuffer.allocate(48);
        int read = sourceChannel.read(buffer2);//Reading data from pipe.sourceChannel into buffer.
        buffer.flip();
        buffer2.flip();
        System.out.println(buffer.equals(buffer2)); //true
    }
}
