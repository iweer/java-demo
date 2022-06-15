package cn.iweer.other;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @File : test.java
 * @Author : wei.hu
 * @Time : 2022/5/31 11:05
 * @Version : V1.0
 */
public class test {

    public test(){
        System.out.println("constructor");
    }
    public test(String a){

    }
    public void test(){
        System.out.println("method");
    }


    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // \u000d System.out.println("hello world");
        new test().test();
    }

}

class a0{
    public static void aaa(){
        System.out.println("aaa method");
    }

    public void aaa2(){
        System.out.println("aaa2 method");
    }
}

class a extends a0{
    private int a=2;
    private static int b=2;

    a(){

    }
    public void aaa2(){
        System.out.println("aaa22 method");
    }
}

class B extends a {

    B(){
    }
    private int c = 3;
    public static void main(String[] args) {
        B b = new B();
        b.aaa2();
        b.aaa();
    }
}


interface fdsa {
    static void main(String[] args) {
        System.out.println("aaa");
    }
}

abstract class dadad{
    public static void main(String[] args) {
        System.out.println("bbbb");
    }
}

enum adadadad{
    a;

    public static void main(String[] args) {
        System.out.println("dadadada");
    }
}

