package netty.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Yale.Wei
 * @date 2018/12/5 11:17
 */
public class EchoServer {
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new EchoServer().bind(port);
    }

    public void bind(int port) throws InterruptedException {
        // 第一步：
        // 配置服务端的NIO线程组

        // 主线程组, 用于接受客户端的连接，但是不做任何具体业务处理，像老板一样，负责接待客户，不具体服务客户
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        // 工作线程组, 老板线程组会把任务丢给他，让手下线程组去做任务，服务客户
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 类ServerBootstrap用于配置Server相关参数，并启动Server
            ServerBootstrap bootstrap = new ServerBootstrap();

            //链式调用
            //配置parentGroup和childGroup
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            // 绑定端口，并启动server，同时设置启动方式为同步
            ChannelFuture f = bootstrap.bind(port).sync();

            System.out.println(EchoServer.class.getName() +
                    " Statup，listening on [" + f.channel().localAddress() +"] to accept client request ......");

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
