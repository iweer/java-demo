package cn.iweer.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @File : main.java
 * @Author : wei.hu
 * @Time : 2022/5/7 11:48
 * @Version : V1.0
 */
public class ProxyMain {

    public static void main(String[] args) {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        ITWorker itWorker = new ITWorker();
        ClassLoader loader = ProxyMain.class.getClassLoader();
        InvocationHandler handler = new WorkHandler(itWorker);
        Worker proxyWorker = (Worker) Proxy.newProxyInstance(loader, new Class[]{Worker.class}, handler);
        System.out.println(proxyWorker.sayHello("1122334"));

        System.out.println(((Worker)new WorkHandler2().bind(itWorker)).sayHello("2222"));

    }

}
