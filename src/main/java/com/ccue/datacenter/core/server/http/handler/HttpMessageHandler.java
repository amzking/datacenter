package com.ccue.datacenter.core.server.http.handler;

import com.ccue.datacenter.core.server.http.url.router.NettyHttpUrlRouter;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;


public class HttpMessageHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        try {
            FullHttpResponse response = new NettyHttpUrlRouter().route(msg);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
