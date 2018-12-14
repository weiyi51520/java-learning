package netty.dev;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

/**
 * @author Yale.Wei
 * @date 2018/12/14 10:34
 */
public class ChannelBufferByteOutput implements ByteOutput{

    private final ByteBuf buffer;

    public ChannelBufferByteOutput(ByteBuf buffer) {
        this.buffer = buffer;
    }

    @Override
    public void write(int b) throws IOException {
        buffer.writeByte(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        buffer.writeBytes(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        buffer.writeBytes(b,off,len);
    }

    @Override
    public void close() throws IOException {}

    @Override
    public void flush() throws IOException {}

    ByteBuf getBuffer(){
        return buffer;
    }
}
