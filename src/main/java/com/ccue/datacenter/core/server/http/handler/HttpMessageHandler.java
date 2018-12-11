package com.ccue.datacenter.core.server.http.handler;

import com.ccue.datacenter.core.server.http.request.HttpRequest;
import com.ccue.datacenter.core.server.http.response.HttpResponse;
import com.ccue.datacenter.core.server.http.url.router.NettyHttpUrlRouter;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

@ChannelHandler.Sharable
public class HttpMessageHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    /**
     * 是否开启对请求对拦截
     */
    private final boolean allowIntercept;

    public HttpMessageHandler() {
        // 默认不拦截
        this.allowIntercept = false;
    }

    public HttpMessageHandler(boolean allowIntecept) {
        this.allowIntercept = allowIntecept;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        FullHttpResponse response = null;
        if (allowIntercept) {

        }
        try {
            response = new NettyHttpUrlRouter().route(msg);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private abstract class HttpRequestProcessor {

        public abstract HttpResponse process(HttpRequest request);
    }


    private class HttpGetRequestProcessor extends HttpRequestProcessor {

        @Override
        public HttpResponse process(HttpRequest request) {
            return null;
        }
    }

}
