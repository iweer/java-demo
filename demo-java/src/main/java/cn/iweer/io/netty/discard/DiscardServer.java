package cn.iweer.io.netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @File : DiscardServer.java
 * @Author : wei.hu
 * @Time : 2022/5/24 17:47
 * @Version : V1.0
 */
public class DiscardServer {
    private final int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // boss负责接受链接，处理accept事件。默认线程数1
        EventLoopGroup workerGroup = new NioEventLoopGroup();  // worker关注accept以外事件，默认线程数是cpu*2，建议根据业务场景调试
        try {
            // 服务端启动，需要创建ServerBootstrap
            // netty把nio代码已经封装好了
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)  // 配置boss和worker
                    // 配置server通道，相当于nio中的ServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    // childHandler 表示worker那些想成配置了一个处理器
                    // 配置初始化channel，也就是给worker配置对应的handler，当收到客户端请求时，分配给指定的handler处理
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler()); //
                        }
                    })
                    //
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            //释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }
}
