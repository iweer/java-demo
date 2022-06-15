package cn.iweer.io.netty.encode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @File : MyServerHandler.java
 * @Author : wei.hu
 * @Time : 2022/5/26 14:16
 * @Version : V1.0
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);

        /** 转成字符串 */
        String message = new String(buffer, Charset.forName("UTF-8"));
        System.out.println("服务器接收数据：" + message);
        System.out.println("总共接收到：" + ++this.count + " 条数据！");

        /** 回复客户端一个随机ID */
        ByteBuf uuid = Unpooled.copiedBuffer(UUID.randomUUID().toString(), Charset.forName("UTF-8"));
        ctx.writeAndFlush(uuid);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
