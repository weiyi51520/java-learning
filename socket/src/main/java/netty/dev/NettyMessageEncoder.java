package netty.dev;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Yale.Wei
 * @date 2018/12/12 16:53
 */
public final class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage>{

    MyMarshallingEncoder encoder;

    public NettyMessageEncoder() throws IOException {
        encoder = MarshallingCodecFactory.buildMarshallingEncoder();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        if (msg == null || msg.getHeader() == null){
            throw new RuntimeException("The encode message is null");
        }
        ByteBuf sendbuf = Unpooled.buffer();
//        sendbuf.writeInt(msg.getHeader().getCrcCode());
        sendbuf.writeInt(msg.getHeader().getLength());
        sendbuf.writeLong(msg.getHeader().getSessionID());
        sendbuf.writeByte(msg.getHeader().getType());
        sendbuf.writeByte(msg.getHeader().getPriority());
        sendbuf.writeInt(msg.getHeader().getAttachment().size());

        String key;
        byte[] keyArray;
        Object value = null;
        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray =key.getBytes("UTF-8");
            sendbuf.writeInt(keyArray.length);
            sendbuf.writeBytes(keyArray);
            value = param.getValue();
            encoder.encode(ctx,value,sendbuf);
        }
        key = null;
        keyArray = null;
        value = null;
        if (msg.getBody() != null){
            encoder.encode(ctx,msg.getBody(),sendbuf);
        }

        //在第0个index位置写入Buffer长度
        sendbuf.setInt(0,sendbuf.readableBytes());
        out.add(sendbuf);
    }
}
