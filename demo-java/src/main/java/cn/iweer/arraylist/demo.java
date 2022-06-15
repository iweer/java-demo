package cn.iweer.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @File : demo.java
 * @Author : wei.hu
 * @Time : 2022/5/9 14:31
 * @Version : V1.0
 */
public class demo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(Arrays.asList("1","2","3","45"));
        list.add(2, "1");
        System.out.println(list.get(0));
        System.out.println(Arrays.asList(1,2,3,45));
        Thread thread = new Thread();


    }

}
