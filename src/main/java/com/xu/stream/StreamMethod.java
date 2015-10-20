package com.xu.stream;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by robert on 2015/10/20.
 */
public class StreamMethod {

    public static void main(String[] args){
        stepOne();
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
}
