package cn.iweer.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @File : NettyDemo.java
 * @Author : wei.hu
 * @Time : 2022/5/16 16:10
 * @Version : V1.0
 */
public class BIoDemo {

    // 当你的应用中连接数比较少，这个方案还是可以接受。当并发连接超过10000 时，context-switching（上下文切换）开销将是明显的。此外，每个线程都有一个默认的堆栈内存分配了 128K 和 1M 之间的空间。考虑到整体的内存和操作系统需要处理 100000 个或更多的并发连接资源，这似乎是一个不理想的解决方案。
    public void blockingIO() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        while ((request = in.readLine()) != null) {
            System.out.println(request);
            if ("Done".equals(request)) {
                break;
            }
        }
    }





}
