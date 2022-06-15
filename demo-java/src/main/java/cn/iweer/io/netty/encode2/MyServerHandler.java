package cn.iweer.io.netty.encode2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @File : MyServerHandler.java
 * @Author : wei.hu
 * @Time : 2022/5/26 14:16
 * @Version : V1.0
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        /** 接收数据，进行处理 */
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println("服务器接收数据：");
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(content, Charset.forName("UTF-8")));
        System.out.println("服务器总共接收到：" + ++this.count + " 条数据！");

        System.out.println();
        System.out.println();

        /** 回复客户端一个随机ID */
        String uuid = UUID.randomUUID().toString();
        int length = uuid.getBytes(Charset.forName("UTF-8")).length;
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(uuid.getBytes(Charset.forName("UTF-8")));
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
