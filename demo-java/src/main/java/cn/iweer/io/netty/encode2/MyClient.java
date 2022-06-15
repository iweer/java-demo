package cn.iweer.io.netty.encode2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @File : MyClient.java
 * @Author : wei.hu
 * @Time : 2022/5/26 14:16
 * @Version : V1.0
 */
public class MyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new MyMsgEncoder()); // ChannelOutboundHandlerAdapter  obj->byte
                            pipeline.addLast(new MyMsgDecoder()); // ChannelInboundHandlerAdapter  byte->obj
                            pipeline.addLast(new MyClientHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect("127.0.0.1", 7000).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }
}
