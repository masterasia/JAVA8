package com.xu.stream;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * Created by robert on 2015/10/20.
 */
public class StreamMethod {

    public static void main(String[] args){
        getSet();
    }

    public static void stepOne(){
        Stream s = getNumbers(true, 100, 100).stream();

        s.filter(a -> (int)a % 17 == 0).forEach(a -> System.out.println(a));
    }

    public static Collection getNumbers(boolean repeat, long seed, int nums){
        Collection collection;
        if (repeat)
            collection = new ArrayList();
        else
            collection = new HashSet();

        Random random = new Random();

        for (int i = 0; i < nums; i++){
            collection.add(random.nextInt((int)seed));
        }

        return collection;
    }

    public static void getSet(){
        long start = Instant.now().toEpochMilli();
        long start_o = System.currentTimeMillis();

        long[] a = new long[20000];

        Arrays.parallelSetAll(a, i -> ThreadLocalRandom.current().nextInt(10000000));

        Arrays.stream(a).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();

        Arrays.parallelSort(a);

        Arrays.stream(a).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println(Instant.now().toEpochMilli() - start);
        System.out.println(System.currentTimeMillis() - start_o);
    }
}
