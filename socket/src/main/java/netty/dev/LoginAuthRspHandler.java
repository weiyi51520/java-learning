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
    private Map<String,Boolean> nodeCheck = new ConcurrentHashMap<>();

    private String[] whitekList={"127.0.0.1","192.168.10.186"}; //白名单

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        System.out.println("Login auth rsp handler receive msg :" + message.toString());
        //如果是握手请求消息进行处理 其他消息直接透传
        if (message.getHeader()!=null && message.getHeader().getType() == MessageType.LOGIN_REQ.value()){
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginRsp = null;
            if (nodeCheck.containsKey(nodeIndex)){ //重复登录 拒绝登录 返回-1
                loginRsp = buildResponse((byte) -1);
            }else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOK = false;
                for (String WIP : whitekList) {
                    if (WIP.equals(ip)){
                        isOK = true;
                        break;
                    }
                }
                loginRsp = isOK? buildResponse((byte) 0) : buildResponse((byte) -1);
                if (isOK){
                    nodeCheck.put(nodeIndex,true);
                }
                System.out.println("The login response is : " + loginRsp + " body [" + loginRsp.getBody() + "]");
                ctx.writeAndFlush(loginRsp);
            }
        }else {
            ctx.fireChannelRead(msg);
        }
    }
    /*构造握手响应消息*/
    private NettyMessage buildResponse(byte body){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(body);
        return message;
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        nodeCheck.remove(ctx.channel().remoteAddress().toString());//删除登录缓存
        ctx.close();
        cause.printStackTrace();
        ctx.fireExceptionCaught(cause);
    }
}
