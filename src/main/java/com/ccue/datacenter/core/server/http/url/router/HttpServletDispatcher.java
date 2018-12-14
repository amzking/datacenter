package com.ccue.datacenter.core.server.http.url.router;

import com.ccue.datacenter.core.server.http.HttpServerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 相当于spring的dispatcherServlet
 */
public class HttpServletDispatcher extends HttpServlet implements IHttpDispatcher {

    public final void init() throws ServletException {

    }


    @Override
    public FullHttpResponse dispatch(FullHttpRequest fullHttpRequest) {
        return null;
    }

    @Override
    public HttpServerContext getServerContext() {
        return null;
    }
}
