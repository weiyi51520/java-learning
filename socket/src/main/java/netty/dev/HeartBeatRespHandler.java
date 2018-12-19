package netty.dev;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Yale.Wei
 * @date 2018/12/13 13:31
 * 服务端心跳应答Handler
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        System.out.println("HeartBeatRespHandler  channelRead:" + message.toString());
        //返回心跳应答消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()){
            System.out.println("HeartBeatRespHandler  channelRead: " + message.toString());

            NettyMessage heartBeat = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_RESP.value());
            heartBeat.setHeader(header);
            System.out.println("Send heart beat response message to client : ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);
        }else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.fireExceptionCaught(cause);
    }

}
