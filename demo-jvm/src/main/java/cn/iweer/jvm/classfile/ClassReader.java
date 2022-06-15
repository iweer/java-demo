package cn.iweer.jvm.classfile;

import java.math.BigInteger;

/**
 * @File : ClassReader.java
 * @Author : wei.hu
 * @Time : 2022/6/8 17:21
 * @Version : V1.0
 */
public class ClassReader {

    private byte[] data;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    //u1
    public int readUint8() {
        byte[] val = readByte(1);
        return byte2int(val);
    }

    //u2
    public int readUint16() {
        byte[] val = readByte(2);
        return byte2int(val);
    }

    //u4
    public long readUint32() {
        byte[] val = readByte(4);
        String str_hex = new BigInteger(1, val).toString(16);
        return Long.parseLong(str_hex, 16);
    }

    public float readUint64TFloat() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).floatValue();
    }

    public long readUint64TLong() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).longValue();
    }

    public double readUint64TDouble() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).doubleValue();
    }

    public int[] readUint16s() {
        int n = this.readUint16();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = this.readUint16();
        }
        return s;
    }

    public byte[] readBytes(int n) {
        return readByte(n);
    }

    private byte[] readByte(int length) {
        byte[] copy = new byte[length];
        System.arraycopy(data, 0, copy, 0, length);
        System.arraycopy(data, length, data, 0, data.length - length);
        return copy;
    }

    private int byte2int(byte[] val) {
        String str_hex = new BigInteger(1, val).toString(16);
        return Integer.parseInt(str_hex, 16);
    }

}
