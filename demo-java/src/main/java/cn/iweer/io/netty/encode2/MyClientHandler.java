package cn.iweer.io.netty.encode2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

/**
 * @File : MyClientHandler.java
 * @Author : wei.hu
 * @Time : 2022/5/26 14:17
 * @Version : V1.0
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /** 使用客户端发送10条数据 */
        for (int i = 0; i < 10; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello Server! -> " + i, Charset.forName("UTF-8"));
            String msg = "Hello Server! -> " + i;
            byte[] content = msg.getBytes(Charset.forName("UTF-8"));
            int length = content.length;
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setContent(content);
            messageProtocol.setLen(length);
            ctx.writeAndFlush(messageProtocol);
        }
//        File file = new File("");
//        FileInputStream in = new FileInputStream(file);
//        FileRegion region = new DefaultFileRegion(in.getChannel(), 0, file.length());
//        ctx.channel().writeAndFlush(region);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        byte[] content = msg.getContent();
        int len = msg.getLen();
        System.out.println("客户端收到消息：");
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(content, Charset.forName("UTF-8")));

        System.out.println("总共接收到：" + ++this.count + " 条数据！");
        System.out.println();
        System.out.println();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
