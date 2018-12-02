package nio.buffers;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author Yale.Wei
 * @date 2018/12/1 17:59
 */
public class BufferCreate {

    public static void main(String[] args) {
        ByteBuffer buffer0 = ByteBuffer.allocate(10);
        if (buffer0.hasArray()){
            System.out.println("buffer0 array: " + ArrayUtils.toString(buffer0.array()));
            System.out.println("buffer0 array offset: " + buffer0.arrayOffset());
        }
        printBuffer(buffer0);

        ByteBuffer buffer1 = ByteBuffer.allocateDirect(10);
        if (buffer1.hasArray()){
            System.out.println("buffer1 array: " + ArrayUtils.toString(buffer1.array()));
            System.out.println("buffer1 array offset: " + buffer1.arrayOffset());
        }
        printBuffer(buffer1);

        byte[] bytes = new byte[10];
        ByteBuffer buffer2 = ByteBuffer.wrap(bytes);
        if (buffer2.hasArray()) {
            System.out.println("buffer2 array: " + ArrayUtils.toString(buffer2.array()));
            System.out.println("Buffer2 array offset: " + buffer2.arrayOffset());
        }
        printBuffer(buffer2);

        byte[] bytes2 = new byte[10];
        ByteBuffer buffer3 = ByteBuffer.wrap(bytes2, 2, 3);
        if (buffer3.hasArray()) {
            System.out.println("buffer3 array: " + ArrayUtils.toString(buffer3.array()));
            System.out.println("Buffer3 array offset: " + buffer3.arrayOffset());
        }
        printBuffer(buffer3);
    }

    private static void printBuffer(Buffer buffer){
        System.out.println("Capacity: " + buffer.capacity());
        System.out.println("Limit: " + buffer.limit());
        System.out.println("Position: " + buffer.position());
        System.out.println("Remaining: " + buffer.remaining());
        System.out.println("=========================");
    }
}
