package com.ccue.datacenter.core.server.http;

import com.ccue.datacenter.core.server.http.handler.HttpHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class HttpNioServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpNioServer.class);

    /**
     * 监听的端口
     */
    private final int port;

    public HttpNioServer (int port) {
        this.port = port;
    }


    public void serve() throws InterruptedException {

        EventLoopGroup listenGroup = new NioEventLoopGroup(1);
        // 默认为线程数的两倍
        EventLoopGroup processGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(listenGroup, processGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 此处添加handler

                            socketChannel.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                            socketChannel.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65535));//将多个消息转化成一个
                            socketChannel.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                            socketChannel.pipeline().addLast("http-server",new HttpHandler());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_RCVBUF, 65535);

            ChannelFuture future = bootstrap.bind(new InetSocketAddress(port)).sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("Server is serving");
                    } else {
                        System.out.println("Server start failed");
                        channelFuture.cause().printStackTrace();
                    }
                }
            });

            future.channel().closeFuture().sync();

        } finally {
            listenGroup.shutdownGracefully();
            processGroup.shutdownGracefully();
        }

    }

}
