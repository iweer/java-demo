package cn.iweer.io.netty.discard;

import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

/**
 * @File : DiscardServerHandler.java
 * @Author : wei.hu
 * @Time : 2022/5/24 17:32
 * @Version : V1.0
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    // 收到消息后如何处理，
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
//        ((ByteBuf) msg).release(); // (3)

        ByteBuf in = (ByteBuf) msg;
        try {
            // 俩种打印方式
            System.out.println(in.toString(CharsetUtil.US_ASCII));
//            while (in.isReadable()) { // (1)
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
            Date date = new Date();
            ctx.write(date + "recv:" + in.toString(CharsetUtil.US_ASCII));
            ctx.writeAndFlush("");
            ChannelHandlerContext channelHandlerContext = ctx.flush();
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
