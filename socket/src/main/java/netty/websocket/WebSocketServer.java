package netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Yale.Wei
 * @date 2018/12/12 08:59
 */
public class WebSocketServer {

    public void run(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("http-codec",new HttpServerCodec());//将请求和应答消息编码或解码成HTTP消息
                            pipeline.addLast("aggregator",new HttpObjectAggregator(65536));//将HTTP消息的多个部分组合成一条完整的HTTP消息
                            pipeline.addLast("http-chunked",new ChunkedWriteHandler());//用于支持浏览器和服务端进行WebSocket通信
                            pipeline.addLast("handler",new WebSocketServerHandler());
                        }
                    });
            Channel channel = bootstrap.bind(port).sync().channel();

            System.out.println("Web socket server started at port " + port + '。');
            System.out.println("Open your browser and navigate to http://localhost:" + port + '/');
            channel.closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }
        new WebSocketServer().run(port);
    }

}
