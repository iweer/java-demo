package cn.iweer.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @File : CompletableFuture.java
 * @Author : wei.hu
 * @Time : 2022/6/2 18:23
 * @Version : V1.0
 */
public class CompletableFutureTester {

    public static void main(String[] args) {

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf1");
            return "cf1";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            System.out.println("cf2");
            return "cf2";
        }), (BiFunction) (o, o2) -> {
            System.out.println(o + "------" +o2);
            return "cf3";
        });
        cf3.thenAccept(System.out::println);
    }


    @Test
    public void demo2(){
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf1");
            return "cf1";
        });
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("cf2");
            return "cf2";
        });
        CompletableFuture<String> cf3 = cf1.thenApply(s -> {
            System.out.println("cf1 -> cf3");
            return "cf3";
        });
        CompletableFuture<String> cf4 = cf1.thenCombine(cf2, (o, o2) -> {
            System.out.println(o + " + " +o2 + " ->  cf4");
            return "cf4";
        });
        CompletableFuture<String> cf5 = cf2.thenApply(s -> {
            System.out.println("cf2 -> cf5");
            return "cf5";
        });
        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf3, cf4, cf5);
        cf6.thenApply(unused -> {
            System.out.println("cf6 success");
            return null;
        });
    }
}
