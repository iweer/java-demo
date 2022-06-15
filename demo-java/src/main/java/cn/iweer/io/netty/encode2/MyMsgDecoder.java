package cn.iweer.io.netty.encode2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @File : MyMsgDecoder.java
 * @Author : wei.hu
 * @Time : 2022/5/26 14:30
 * @Version : V1.0
 */
public class MyMsgDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMsgDecoder.decode called...");

        /** 这里需要将获取到的二进制字节码转成MessageProtocol 数据包 */
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        /** 封装成MessageProtocol 对象，放入out，传给下一个Handler 进行业务处理 */
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(content);

        out.add(messageProtocol);
    }
}
