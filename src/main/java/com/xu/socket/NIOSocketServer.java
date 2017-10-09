package com.xu.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class NIOSocketServer {

    public NIOSocketServer() {
        final AsynchronousServerSocketChannel assc;
        try {
            assc = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(10101));
            assc.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    assc.accept(null, this);
                    result.write(ByteBuffer.wrap("".getBytes()));
                    ByteBuffer bb = ByteBuffer.allocate(4096);
                    try {

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
