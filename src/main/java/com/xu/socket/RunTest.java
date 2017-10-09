package com.xu.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class RunTest {
    public static void main(String[] args){
        clientTest();
    }

    public static void serverTest(){
        try {
            ServerSocket ss = new ServerSocket(9999);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clientTest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input what server you check.");
        String server = scanner.nextLine();
        try {
            Socket getSocket = new Socket(server, 10101);
            PrintStream ps = new PrintStream(getSocket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(getSocket.getInputStream()));

            ps.println("GET  " + server + " HTTP/1.0");
            ps.println();
            String line = br.readLine();
            String sendLine = "";
            while(line != null ){
                System.out.println("[client] : " + line);
                System.out.println("please input what path you connect.");
                sendLine = scanner.nextLine();
                ps.println(sendLine);
                line = br.readLine();
            }
            br.close();
            ps.close();
            getSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
