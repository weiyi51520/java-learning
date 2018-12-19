package netty.dev;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * @author Yale.Wei
 * @date 2018/12/13 10:10
 * 客户端握手认证Handler 通道激活时发送握手请求
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter{

    /*通道激活发送握手请求*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        System.out.println("LoginAuthReqHandler channelActive :" + message);
        ctx.writeAndFlush(message);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        System.out.println("LoginAuthReqHandler channelRead : " + message.toString());
        //如果是握手应答消息 需要判断是否认证成功
        if (message.getHeader()!=null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()){
            byte loginResult = (byte) message.getBody();
            if (loginResult != 0){ //握手失败
                ctx.close();
            }else {
                System.out.println("Login is ok : " + message);
                ctx.fireChannelRead(msg);
            }
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
