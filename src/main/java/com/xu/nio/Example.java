package com.xu.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * Created by robert.xu on 2017/8/17 0017.
 */
public class Example {
//    public static void main(String[] args){
//        Integer a = 42;
//        Long b = 42L;
//        System.out.println(b.equals(42L));
//    }
private static int j = 0;
    private static Boolean methodB(int k) {
        j += k;
        return true;
    }
    public static void methodA(int i) {
        boolean b;
        b = i < 10 | methodB(4);
        b = i < 10 || methodB(8);
    }
    public static void main(String args[]) {
        methodA(0);
        System.out.println(j);
    }

    public static void byteBufferTest() {
//        InputStream is = System.in;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        try {
//            String name = br.readLine();
//            System.out.println(name.split(":")[1]);
//            String age = br.readLine();
//            System.out.println(age.split(":")[1]);
//            String phone = br.readLine();
//            System.out.println(phone.split(":")[1]);
//            String email = br.readLine();
//            System.out.println(email.split(":")[1]);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            RandomAccessFile raf = new RandomAccessFile("f:/new.txt", "rw");
            ByteBuffer bb = ByteBuffer.allocate(1024);
            FileChannel inChannel = raf.getChannel();
            int readNum = inChannel.read(bb);
            while (readNum != -1) {
                System.out.println("read : " + readNum);
                bb.flip();
                while(bb.hasRemaining()){
                    System.out.println("[now read]:" + (char)bb.get() + ":[position:" + bb.position() + "; capacity"
                            + bb.capacity() + ";limit" + bb.limit() + " ]");
                }

                bb.clear();
                readNum = inChannel.read(bb);
            }
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

