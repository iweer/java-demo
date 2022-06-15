package cn.iweer.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @File : WorkHandler.java
 * @Author : wei.hu
 * @Time : 2022/5/7 11:28
 * @Version : V1.0
 */
public class WorkHandler implements InvocationHandler {

    private Object obj;

    public WorkHandler(Object obj){
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj, args);
    }
}
