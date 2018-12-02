package nio.buffers;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author Yale.Wei
 * @date 2018/12/1 17:39
 */
public class BufferAccess {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        printBuffer(buffer);

        buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        printBuffer(buffer);

        buffer.flip();
        printBuffer(buffer);


        System.out.println(buffer.get()+" "+buffer.get());
        printBuffer(buffer);

        buffer.mark();
        printBuffer(buffer);

        //return position to previous mark position  after get twice
        System.out.println("" + (char) buffer.get() + (char) buffer.get());
        printBuffer(buffer);

        buffer.reset();

        printBuffer(buffer);

        buffer.compact();
        printBuffer(buffer);

        buffer.clear();
        printBuffer(buffer);
    }



    private static void  printBuffer(Buffer buffer) {
        System.out.println("[limit=" + buffer.limit()
                +", position = " + buffer.position()
                +", capacity = " + buffer.capacity()
                +", array = " + ArrayUtils.toString(buffer.array())+"]");
    }
}
