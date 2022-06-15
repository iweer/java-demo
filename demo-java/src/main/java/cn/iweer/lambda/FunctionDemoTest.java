package cn.iweer.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @File : FunctionDemoTest.java
 * @Author : wei.hu
 * @Time : 2022/5/17 11:46
 * @Version : V1.0
 */
public class FunctionDemoTest {
    public static void main(String[] args) {
        Consumer<Integer> c = x -> System.out.println(x + 1);
        c.accept(1232);
        Function<Integer, Integer> f = x -> x + 1;
        Integer i2 = f.apply(1);
        System.out.println("i2 = " + i2);
        Predicate<Integer> p = x -> String.valueOf(x).startsWith("1");
        boolean i3 = p.test(123);
        System.out.println("i3 = " + i3);
        Supplier<String> s = () -> "fdasfdsa";
        String s1 = s.get();
        System.out.println("s1 = " + s1);
        Optional<Integer> o = Optional.ofNullable(null);
        if (o.isPresent()) {
            System.out.println("o = " + o);
        }
    }

    @Test
    public void lambda(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        // 外部迭代
        for (int i = 0; i < list.size(); i++) {
            System.out.println("integer = " + list.get(i));
        }

        for (Integer integer : list) {
            System.out.println("integer = " + integer);
        }
        // 内部迭代
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
        list.forEach((Integer e) -> System.out.println("integer = " + e));
        list.forEach((e) -> System.out.println("integer = " + e));
        // 只有一个参数，可以省略括号，0 个或者2个及以上，必须有括号
        list.forEach(e -> System.out.println("integer = " + e));
        // 如果没在元素做什么操作，加1 减1，可以如下，叫做method reference
        list.forEach(System.out::println);
    }
}
