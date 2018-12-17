package com.ccue.datacenter.core.server.http.handler;

import com.ccue.datacenter.core.server.http.request.HttpIntercepterManager;
import com.ccue.datacenter.core.server.http.request.HttpRequest;
import com.ccue.datacenter.core.server.http.response.HttpResponse;
import com.ccue.datacenter.core.server.http.url.router.IHttpDispatcher;
import com.ccue.datacenter.core.server.http.url.router.NettyHttpDispatcher;
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
     * 是否开启对请求对拦截
     */
    private final boolean allowIntercept;

    /**
     * 请求分发，mvc 入口
     */
    private IHttpDispatcher dispatcher;

    /**
     * 拦截时返回默认消息
     */
    private static final String DEFAULT_INTERCEPT_MSG = "your request was intercepted!";

    /**
     * 默认FullHtpResponse
     */
    private FullHttpResponse DEFAULT_INTERCEPT_RESPONSE = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
            Unpooled.wrappedBuffer(DEFAULT_INTERCEPT_MSG.getBytes()));

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
            response.headers().set(CONTENT_TYPE, "application/xml");
            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            try {
                FullHttpResponse response = dispatcher.dispatch(msg);
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
