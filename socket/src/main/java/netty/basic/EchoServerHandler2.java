package netty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Yale.Wei
 * @date 2018/12/5 11:09
 */
public class EchoServerHandler2 extends ChannelInboundHandlerAdapter{

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded2");
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive2 ...");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered2 ...");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered2 ...");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive2 ...");
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        System.out.println("channelRead2 ...");
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server accept message : "+ in.toString(CharsetUtil.UTF_8));
//        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)  {
        System.out.println("channelReadComplete2 ...");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("exceptionCaught2 ...");
        cause.printStackTrace();
        ctx.close();
    }

}
