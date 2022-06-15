package cn.iweer.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @File : DemoStream.java
 * @Author : wei.hu
 * @Time : 2022/5/16 16:08
 * @Version : V1.0
 */
public class DemoStream {

    @Test
    public void intStreamForEachDemo() {
        IntStream range = IntStream.range(1, 10);
        range.forEach(System.out::println);
        System.out.println("range = " + range);
    }
    /*
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9
     * range = java.util.stream.IntPipeline$Head@4d591d15
     *
     * 进程已结束,退出代码0
     * */

    @Test
    public void IntStreamSkip(){
        IntStream range = IntStream.range(1, 10);
        range.skip(5).forEach(x -> System.out.println("x = " + x));
        System.out.println("range = " + range);
    }

    /*
    skip 跳过最开始n 个元素
    x = 6
    x = 7
    x = 8
    x = 9
    range = java.util.stream.IntPipeline$Head@5b480cf9
    */

    @Test
    public void IntStreamSum(){
        Date start = new Date();
        int maxNum= 99999999;
        long num = IntStream.range(1, maxNum).sum();
        System.out.println("cost:"+ (new Date().getTime() - start.getTime())+ ",sum:"+num);

        start = new Date();
        long num2 = IntStream.range(1, maxNum).parallel().sum();
        System.out.println("cost:"+ (new Date().getTime() - start.getTime())+ ",sum:"+num2);
    }
//    cost:117,sum:787459713
//    cost:24,sum:787459713

    @Test
    public void IntStreamSorted(){
        System.out.println("Bfdsa".charAt(5-1));
        Stream.of("Cfdazb", "Ave", "Bfdsa")
                .sorted((x, y) -> x.charAt(x.length() -1) - y.charAt(y.length() -1))
                //.forEach(System.out::println);
                .findFirst()
                .ifPresent(System.out::println);
        // Bfdsa
    }


    @Test
    public void IntStreamFilterSorted(){
        String[] names = {"abbb", "abcxz", "fdafdas", "fdasfdsa", "adfdsafds", "a21321321"};
        Stream<String> stream = Arrays.stream(names);
        stream.filter(x -> x.startsWith("a"))
                .sorted().forEach(System.out::println);
    }


    @Test
    public void IntStreamAverage(){
        IntStream intStream = Arrays.stream(new int[]{2, 4}).map(x -> x * x);
        OptionalDouble average = intStream.average();
        average.ifPresent(System.out::println);
    }

    @Test
    public void IntStreamMap(){
        List<String> people = Arrays.asList("Ab", "ACd", "EF", "Gh");
        Stream<String> stream = people.stream();
        stream.map(String::toLowerCase)
                .filter(x -> x.startsWith("a"))
                .forEach(System.out::println);
        /*
        ab
        acd
        */
    }

    @Test
    public void IntStreamFileLines(){
        try (Stream<String> lines = Files.lines(Paths.get("/Users/mac/www/demo/demo-java/src/main/java/cn/iweer/stream/data.txt"));) {

            lines.filter(x -> x.length() <= 13)
                    .sorted()
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        dsaf
        dsafdsfsdsads
        esdfsa
        f
        fdsa
        sadf
        saf
        */
    }

    @Test
    public void IntStreamToList(){
        try {
            List<String> strings = Files.lines(Paths.get("/Users/mac/www/demo/demo-java/src/main/java/cn/iweer/stream/data.txt"))
                    .filter(x -> x.contains("d"))
                    .collect(Collectors.toList());
            strings.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        dsaf
        dsafdsfsdsads
        sadf
        dasdadfagagagagasfgasgag
        esdfsa
        fdsa
        */
    }

    @Test
    public void IntStreamFilterCount(){
        Stream<String> rows = null;
        try {
            rows = Files.lines(Paths.get("/Users/mac/www/demo/demo-java/src/main/java/cn/iweer/stream/data2.txt"));
            long count = rows
                    .map(x -> x.split(","))
                    .filter(x -> x.length < 3)
                    .count();
            System.out.println("count = " + count);
            rows.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IntStreamMapFilter(){
        Stream<String> rows = null;
        try {
            rows = Files.lines(Paths.get("/Users/mac/www/demo/demo-java/src/main/java/cn/iweer/stream/data2.txt"));
            rows
                    .map(x -> x.split(","))
                    .filter(x -> x.length < 3)
                    //.filter(x -> Integer.parseInt(x[1]) > 16)
                    .forEach((String[] x) -> Arrays.stream(x).forEach(System.out::println));
            rows.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        dsaf
        dsafdsf
        sdsads
        sa
        df
        saf
        esdfsa
        f
        fdsa
        * **/
    }

    @Test
    public void IntStreamFilterMap(){
        Stream<String> rows = null;
        try {
            rows = Files.lines(Paths.get("/Users/mac/www/demo/demo-java/src/main/java/cn/iweer/stream/data2.txt"));
            Map<String, Long> collect = rows
                    .map(x -> x.split(","))
                    .filter(x -> x.length < 3)
                    .collect(Collectors.toMap(x -> x[0], x -> Arrays.stream(x).count()));
            rows.close();

            for (Map.Entry<String, Long> entry : collect.entrySet()) {
                System.out.println("key = " + entry.getKey());
                System.out.println("value = " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IntStreamReduction(){
        Stream<Double> stream = Stream.of(7.2, 5.6, 4.5);
        Double reduce = stream.reduce(0.0, (Double a, Double b) -> a + b);
        // 0.0 是开始的值，a 是总和，b 是新元素
        System.out.println("reduce = " + reduce);
        /*
        reduce = 17.3
        */
    }

    @Test
    public void IntStreamIntSummaryStatistics(){
        IntSummaryStatistics summaryStatistics = IntStream.of(3, 5, 6, 2, 4, 3, 1).summaryStatistics();
        System.out.println("summaryStatistics = " + summaryStatistics);

        /*
        summaryStatistics = IntSummaryStatistics{count=7, sum=24, min=1, average=3.428571, max=6}
        */
    }


}
