package cn.iweer.thread;

/**
 * @File : ThreadState.java
 * @Author : wei.hu
 * @Time : 2022/5/16 09:43
 * @Version : V1.0
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{});
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
    }
}
