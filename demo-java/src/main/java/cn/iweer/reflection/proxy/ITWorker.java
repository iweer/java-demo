package cn.iweer.reflection.proxy;

/**
 * @File : ITWorker.java
 * @Author : wei.hu
 * @Time : 2022/5/7 11:47
 * @Version : V1.0
 */
public class ITWorker implements Worker{


    @Override
    public String sayHello(String name) {
        return "php is best:"+name;
    }
}
