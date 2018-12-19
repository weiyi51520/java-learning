package netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * @author Yale.Wei
 * @date 2018/12/5 11:37
 */
public class ByteBufDemo {
    private final static Random RANDOM = new Random();
    private final static ByteBuf BYTE_BUF = Unpooled.buffer();

    public static void main(String[] args) {
        directBuffer();
    }

    private static void directBuffer() {
        ByteBuf directBuffer = Unpooled.directBuffer(16);
        if (!directBuffer.hasArray()){
            int i = 0;
            while (directBuffer.writableBytes()>0){
                directBuffer.writeByte(i++);
            }
            int length = directBuffer.readableBytes();
            byte[] array = new byte[length];
            directBuffer.getBytes(directBuffer.readerIndex(), array);
            handleArray(array, 0, length);
        }
    }

    private static void heapBuffer() {
        ByteBuf heapBuf = Unpooled.buffer(16);
        if (heapBuf.hasArray()){
            int i = 0;
            while (heapBuf.writableBytes()>0){
                heapBuf.writeByte(i++);
            }
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            handleArray(array,offset,length);
        }
    }

    private static void handleArray(byte[] array, int offset, int len){
        for (int i = 0; i <len ; i++) {
            System.out.println(array[offset+i]);
        }
    }

    private static void compositeByteBuf() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf headerBuf = Unpooled.copiedBuffer("Header", utf8);
        ByteBuf bodyBuf = Unpooled.copiedBuffer("This is body", utf8);
        messageBuf.addComponents(headerBuf, bodyBuf);
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }

        messageBuf.removeComponent(0); // remove the header
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }
    }

    private static void byteBufCopy() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf copy = buf.copy(0, 15);
        System.out.println(copy.toString(utf8));
        buf.setByte(0, (byte)'J');
        System.out.println(copy.toString(utf8));
    }

    private static void byteBufSlice() {
        Charset utf8 = CharsetUtil.UTF_8;
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 15);
        System.out.println(sliced.toString(utf8));
        buf.setByte(0, (byte)'J');
        System.out.println(sliced.toString(utf8));
    }

    private static void byteProcessor() {
        ByteBuf buffer = Unpooled.copiedBuffer("Netty\r in Action rocks!", CharsetUtil.UTF_8);
        int index = buffer.forEachByte(ByteProcessor.FIND_CR);
        System.out.println(index);
    }

    private static void writeAndRead() {
        ByteBuf buffer = Unpooled.buffer(20);
        int i = 0;
        while (buffer.writableBytes() >= 4){
            buffer.writeInt(i++);
        }

        while (buffer.isReadable()){
            System.out.println(buffer.readInt());
        }
    }

    private static void bufWriteRead() {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", CharsetUtil.UTF_8);
        System.out.println((char)buf.readByte());
        System.out.println("readerIndex = " + buf.readerIndex() + "; writerIndex = " + buf.writerIndex());

        buf.writeByte((byte)'?');
        System.out.println("readerIndex = " + buf.readerIndex() + "; writerIndex = " + buf.writerIndex());

        buf.readByte();
        System.out.println("readerIndex = " + buf.readerIndex() + "; writerIndex = " + buf.writerIndex());
    }

    private static void bufSetGet() {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks", CharsetUtil.UTF_8);
        System.out.println((char) buf.getByte(0));
        System.out.println("readerIndex = " + buf.readerIndex() + "; writerIndex = " + buf.writerIndex());
        buf.setByte(0,'B');
        System.out.println((char) buf.getByte(0));
        System.out.println("readerIndex = " + buf.readerIndex() + "; writerIndex = " + buf.writerIndex());
    }
}
