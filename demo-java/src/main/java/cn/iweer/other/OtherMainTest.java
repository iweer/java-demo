package cn.iweer.other;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @File : Other.java
 * @Author : wei.hu
 * @Time : 2022/5/17 09:54
 * @Version : V1.0
 */
public class OtherMainTest {

    final String a = "aaa";

    final int c = 2;
//
//    private static final sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();;
    @Test
    public void demoStatic(){

        final String b = "aaa";
        System.out.println(b);
    }

    public static void main(String[] args) {
        System.out.println(1234567677);
        System.out.println(1234567677);
        BigDecimal a= BigDecimal.valueOf(1222);
        BigDecimal b = BigDecimal.valueOf(2222);
        swap(a, b);
        System.out.println(a.toString() + " " +b.toString());
        Integer a3= 1222;
        Integer b3 = 2222;
        HashMap<String, Integer> map = new HashMap();
        map.put("a", a3);
        map.put("b", b3);
        swap4(map);
        System.out.println(map.get("a") + " " +map.get("b"));

        String x = "12314114";
        x = "xxxxdasd";

    }

    public static void swap(BigDecimal a, BigDecimal b){
        a = a.add(b);
        b = a.subtract(b);
        a = a.subtract(b);
    }

    public static void swap2(int a, int b){
        a = a + b;
        b = a - b;
        a = a - b;
    }

    public static void swap3(Integer a, Integer b){
        // U.compareAndSwapInt(a, 4, b, b);
        a = a + b;
        b = a - b;
        a = a - b;
    }

    public static void swap4(Map<String, Integer> m){
        int a = m.get("a");
        int b = m.get("b");
        m.put("b", a);
        m.put("a", b);

    }

    @Test
    public void testSwapString(){
        String a = "aaaaa";
        System.out.println(a);
        swapString(a);
        System.out.println(a);
    }

    public static void swapString(String a){
        a = "xxxxxdada";
        System.out.println(a);
    }

    private String name = "";
    private void setName(String name){
        this.name = name;
    }
    private String getName(){
        return this.name;
    }


    @Test
    public void testSwapObj(){
        OtherMainTest a = new OtherMainTest();
        a.setName(a.getName());
        System.out.println(a);
        swapObj(a);
        System.out.println(a.getName());
        System.out.println(a);
    }
    public static void swapObj(OtherMainTest a){
        a.setName("bbbb");
        System.out.println(a.getName());

        System.out.println(a);
        a = new OtherMainTest();
        a.setName("ccccc");
        System.out.println(a.getName());

        System.out.println(a);
    }







}
