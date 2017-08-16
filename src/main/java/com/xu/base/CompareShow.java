package com.xu.base;

/**
 * Created by robert.xu on 2017/8/16 0016.
 */
public class CompareShow {

    public static void main(String[] args){
        compareInt();
        compareString();
    }
    public static void compareInt() {
        int a = 500, b = 500;
        System.out.println(a == b);
        //below 128?
        Integer ai = 128, bi = 128;
        System.out.println(ai == bi);
    }

    public static void compareString(){
        String str = new StringBuffer("compareIn").append("t").toString();
        //intern from a pool, maybe the word is already in.
        System.out.println(str.intern() == str);
    }
}
