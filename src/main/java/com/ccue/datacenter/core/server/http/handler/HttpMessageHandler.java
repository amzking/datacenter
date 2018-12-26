package com.ccue.datacenter.core.server.http.handler;

import com.ccue.datacenter.core.server.http.request.HttpIntercepterManager;
import com.ccue.datacenter.core.server.http.url.dispatch.IHttpDispatcher;
import com.ccue.datacenter.core.server.http.url.dispatch.NettyHttpDispatcher;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONTENT_LENGTH;

@ChannelHandler.Sharable
public class HttpMessageHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    /**
     * 拦截时返回默认消息
     */
    private static final String DEFAULT_INTERCEPT_MSG = "{\"code\":200, \"msg\":\"your request was intercepted!\"}";
    /**
     * 默认FullHtpResponse
     */
    public static FullHttpResponse DEFAULT_INTERCEPT_RESPONSE = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
            Unpooled.wrappedBuffer(DEFAULT_INTERCEPT_MSG.getBytes()));

    static {
        DEFAULT_INTERCEPT_RESPONSE.headers().set(CONTENT_TYPE, "application/json");
        DEFAULT_INTERCEPT_RESPONSE.headers().setInt(CONTENT_LENGTH, DEFAULT_INTERCEPT_RESPONSE.content().readableBytes());
    }

    /**
     * 是否开启对请求对拦截
     */
    private final boolean allowIntercept;
    /**
     * 请求分发，mvc 入口, 持有一个HttpServerContext
     */
    private IHttpDispatcher dispatcher;

    public HttpMessageHandler() {
        // 默认不拦截
        this.allowIntercept = false;
        this.dispatcher = new NettyHttpDispatcher();
    }

    public HttpMessageHandler(boolean allowIntercept, IHttpDispatcher dispatcher) {
        this.allowIntercept = allowIntercept;
        this.dispatcher = dispatcher;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        if (allowIntercept && !HttpIntercepterManager.getInstance().accept(msg)) {
            FullHttpResponse response = DEFAULT_INTERCEPT_RESPONSE;
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            try {
                // 请求转至控制器
                FullHttpResponse response = dispatcher.dispatch(msg);
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
