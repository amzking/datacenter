package com.ccue.datacenter.core.server.http.url.router;

import com.ccue.datacenter.core.server.http.HttpServerContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 相当于spring的dispatcherServlet
 */
public class HttpServletDispatcher extends HttpServlet implements IDispatcher<HttpServletRequest, HttpServletResponse> {

    public final void init() throws ServletException {

    }

    @Override
    public HttpServletResponse dispatch(HttpServletRequest httpServletRequest) {


        return null;
    }

    @Override
    public HttpServerContext getServerContext() {
        return null;
    }
}
