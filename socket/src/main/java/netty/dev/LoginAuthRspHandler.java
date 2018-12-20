package netty.dev;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Yale.Wei
 * @date 2018/12/13 10:10
 * 服务端握手接入和安全认证代码 给客户端返回握手响应消息
 */
public class LoginAuthRspHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        System.out.println("LoginAuthRspHandler  channelRead:" + message);
        //如果是握手请求消息进行处理 其他消息直接透传
        if (message.getHeader()!=null && message.getHeader().getType() == MessageType.LOGIN_REQ.value()){
            System.out.println("Login is OK,message : " + message);
        }
        ctx.writeAndFlush(buildResponse("Login auth success"));
    }

    /*构造握手响应消息*/
    private NettyMessage buildResponse(Object body){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(body);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
        ctx.fireExceptionCaught(cause);
    }
}
