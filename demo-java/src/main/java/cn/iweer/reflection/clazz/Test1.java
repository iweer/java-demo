package cn.iweer.reflection.clazz;

import cn.iweer.reflection.clazz.model.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test1 {
    @Test
    public void clazz() throws InstantiationException, IllegalAccessException {
        System.out.println("aaa");
        Class clazz = User.class;
        User user = (User) clazz.newInstance();
        user.setId(1);
        user.setAge(18);
        user.setName("hw");
        System.out.printf(user.toString());
    }


    @Test
    public void clazzForName() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName("cn.iweer.reflection.clazz.model.User");
        User user = (User) clazz.newInstance();
        User user2 = (User) clazz.newInstance();
        user.setId(1);
        user.setAge(18);
        user.setName("hw");
        System.out.println(user.toString());
        for(Field field: clazz.getDeclaredFields()){
            System.out.println(field.getType());
            String methodStr = "get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
            field.setAccessible(true);
            field.set(user2, clazz.getMethod(methodStr).invoke(user));
        }
        System.out.println(user2.toString());
    }

    @Test
    public void lombok() {
        System.out.println(User.builder().id(1).name("tanzongniubi").age(20).build().toString());
        User user = new User(2, "tz", 20);
        System.out.println(user.toString());
    }

}
