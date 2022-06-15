package cn.iweer.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @File : WorkHander2.java
 * @Author : wei.hu
 * @Time : 2022/5/7 11:56
 * @Version : V1.0
 */
public class WorkHandler2 implements InvocationHandler {

    private Object obj;

    public Object bind(Object obj){
        this.obj = obj;
        return Proxy.newProxyInstance(this.obj.getClass().getClassLoader(), this.obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj, args);
    }
}
