package com.ccue.datacenter.core.server.http.handler;

import com.ccue.datacenter.core.server.http.request.HttpIntercepterManager;
import com.ccue.datacenter.core.server.http.request.HttpRequest;
import com.ccue.datacenter.core.server.http.response.HttpResponse;
import com.ccue.datacenter.core.server.http.url.router.IDispatcher;
import com.ccue.datacenter.core.server.http.url.router.IHttpDispatcher;
import com.ccue.datacenter.core.server.http.url.router.NettyHttpDispatcher;
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

    /**
     * 请求分发，mvc 入口
     */
    private IHttpDispatcher dispatcher;

    public HttpMessageHandler() {
        // 默认不拦截
        this.allowIntercept = false;
        this.dispatcher = new NettyHttpDispatcher();
    }

    public HttpMessageHandler(boolean allowIntecept, IHttpDispatcher dispatcher) {
        this.allowIntercept = allowIntecept;
        this.dispatcher = dispatcher;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        FullHttpResponse response = null;
        if (allowIntercept && HttpIntercepterManager.getInstance().intercept(msg)) {
            // 默认返回
            // 考虑返回状态码
            response = null;
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
        try {
            response = dispatcher.dispatch(msg);
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
