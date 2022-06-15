package cn.iweer.thread;

import org.junit.Test;

import java.util.concurrent.Exchanger;

/**
 * @File : ExchangerData.java
 * @Author : wei.hu
 * @Time : 2022/6/14 16:57
 * @Version : V1.0
 */
public class ExchangerData {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                String data = "hello world123";
                System.out.println(Thread.currentThread().getName()+data);
                data = exchanger.exchange(data);
                System.out.println(Thread.currentThread().getName()+data);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                String data = "hello world321";
                System.out.println(Thread.currentThread().getName()+data);
                data = exchanger.exchange(data);
                System.out.println(Thread.currentThread().getName()+data);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }).start();;


    }



    @Test
    public void testInt(){
        Exchanger<Object> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                Object data = 123;
                System.out.println(Thread.currentThread().getName()+data);
                data = exchanger.exchange(data);
                System.out.println(Thread.currentThread().getName()+data);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Object data = 321;
                System.out.println(Thread.currentThread().getName()+data);
                data = exchanger.exchange(data);
                System.out.println(Thread.currentThread().getName()+data);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }).start();;
    }
}
