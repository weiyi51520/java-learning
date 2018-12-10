package netty.timer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Yale.Wei
 * @date 2018/12/6 16:09
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //做类型转换，将msg转换成Netty的ByteBuf对象。
//        //ByteBuf类似于JDK中的java.nio.ByteBuffer 对象，不过它提供了更加强大和灵活的功能。
//        ByteBuf buf = (ByteBuf) msg;
//        //通过ByteBuf的readableBytes方法可以获取缓冲区可读的字节数，
//        //根据可读的字节数创建byte数组
//        byte[] req = new byte[buf.readableBytes()];
//        //通过ByteBuf的readBytes方法将缓冲区中的字节数组复制到新建的byte数组中
//        buf.readBytes(req);
//        //通过new String构造函数获取请求消息。
//        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
//        System.out.println("The time server receive order : " + body + " ; the counter is : " + ++counter);
//        //如果是"QUERY TIME ORDER"则创建应答消息，
//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
//                System.currentTimeMillis()).toString() : "BAD ORDER";
//        currentTime = currentTime + System.getProperty("line.separator");
//        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//        //通过ChannelHandlerContext的write方法异步发送应答消息给客户端。
//        ctx.writeAndFlush(resp);
//    }

    /**
     * 解决半包问题 不对msg做额外处理和编码
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + " ; the counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
                System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
