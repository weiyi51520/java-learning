package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yale.Wei
 * @date 2018/12/4 10:28
 */
public class ScatteringReads {
    public static void main(String[] args) throws IOException {
        File file = new File("");

        FileInputStream fin = new FileInputStream(file);
        FileChannel channel = fin.getChannel();

        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] buffers = {header, body};

        channel.read(buffers);

    }
}
