package netty.dev;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static netty.dev.NettyConstant.*;

/**
 * @author Yale.Wei
 * @date 2018/12/13 14:37
 */
public class NettyClient {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port,String host) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline cp = ch.pipeline();
                            cp.addLast(new NettyMessageDecoder(1024*1024,4,4));
                            cp.addLast(new NettyMessageEncoder());
                            cp.addLast(new ReadTimeoutHandler(20));
                            cp.addLast(new LoginAuthReqHandler());
                            cp.addLast(new HeartBeatReqHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(host,port).sync(); //绑定了本地端口 用于重复登录保护
            future.channel().closeFuture().sync();
        } finally {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    try {
                        connect(PORT,REMOTEIP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClient().connect(PORT,REMOTEIP);
    }
}
