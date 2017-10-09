package com.xu.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer extends Thread {
    private ServerSocket serverSocket;

    private int port;

    private boolean isRunning = false;

    public SimpleSocketServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try{
            serverSocket = new ServerSocket(port);
            this.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopServer(){
        this.isRunning = false;
        this.interrupt();
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                System.out.println(" wait for connect");
                Socket socket = serverSocket.accept();
                RequestHandler rh = new RequestHandler(socket);
                rh.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleSocketServer sss = new SimpleSocketServer(10101);
        sss.startServer();
    }
}

class RequestHandler extends Thread {
    private Socket socket;
    private static int index = 0;
    RequestHandler(Socket socket){
        this.socket = socket;
        index++;
    }

    @Override
    public void run() {
        try {
            System.out.println("[Server][RequestHandler] controller new connection");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(" welcome connect server 1.0, your the " + index);
            pw.flush();
            String line = br.readLine();
            while (null != line && !line.isEmpty()){
                System.out.println("[Server][RequestHandler] received :" + line);
                pw.println("hi :  " + index + " server return : " + line);
                pw.flush();
                line = br.readLine();
            }
            pw.close();
            br.close();
            socket.close();
            System.out.println("[Server][RequestHandler] connection is broken. index is " + index);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
