package com.ccue.datacenter.core.server.http.url.router;

import com.ccue.datacenter.core.server.http.HttpServerContext;
import com.ccue.datacenter.core.server.http.transform.HttpServletRequestTransform;
import com.ccue.datacenter.core.server.http.transform.ITransform;
import com.ccue.datacenter.core.server.http.transform.NettyRequestTransform;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * 相当于spring的dispatcherServlet
 *
 * 适配MVC
 *
 * 支持 servlet
 */
public class HttpServletDispatcher implements IHttpDispatcher {

    private HttpServlet servlet;

    private HttpServletRequestTransform transform;

    public HttpServletDispatcher(HttpServlet servlet) {
        this.servlet = servlet;
    }


    @Override
    public FullHttpResponse dispatch(FullHttpRequest fullHttpRequest) {
        // 需要将netty的request 转为 httpServletRequest
        //HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper();
        HttpServletRequest request = transform.transform(fullHttpRequest);
        try {
            servlet.service(request, null);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HttpServerContext getServerContext() {
        return null;
    }
}
