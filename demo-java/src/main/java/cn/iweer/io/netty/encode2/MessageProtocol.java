package cn.iweer.io.netty.encode2;

/**
 * @File : MessageProtocol.java
 * @Author : wei.hu
 * @Time : 2022/5/26 14:29
 * @Version : V1.0
 */
public class MessageProtocol {
    private int len;
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
