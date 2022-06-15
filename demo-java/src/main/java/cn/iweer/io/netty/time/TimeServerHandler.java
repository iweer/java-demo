package cn.iweer.io.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
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
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        System.out.println(System.currentTimeMillis());
        int now = (int) (System.currentTimeMillis()/1000);

        System.out.println("return time:"+now);
        time.writeInt(now);

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener((ChannelFutureListener) channelFuture -> {
            assert f == channelFuture;
            ctx.close();
        });
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
