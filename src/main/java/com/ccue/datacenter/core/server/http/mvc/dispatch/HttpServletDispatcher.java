package com.ccue.datacenter.core.server.http.mvc.dispatch;

import com.ccue.datacenter.core.server.http.servlet.DefaultHttpServletResponse;
import com.ccue.datacenter.core.server.http.mvc.transform.HttpServletRequestTransform;
import com.ccue.datacenter.core.server.http.mvc.transform.HttpServletResponseTransform;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 适配spring的dispatcherServlet
 *
 * 适配MVC
 *
 * 支持 servlet
 */
public class HttpServletDispatcher extends AbstractHttpDispatcher {

    private HttpServlet servlet;

    private HttpServletRequestTransform requestTransform;

    private HttpServletResponseTransform responseTransform;

    public HttpServletDispatcher(HttpServletRequestTransform requestTransform, HttpServletResponseTransform responseTransform) {
        this.requestTransform = requestTransform;
        this.responseTransform = responseTransform;
    }

    /**
     * 需要将netty的request 转为 httpServletRequest
     * @param fullHttpRequest
     * @return
     */
    @Override
    public FullHttpResponse dispatch(FullHttpRequest fullHttpRequest) {
        //HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper();
        HttpServletRequest request = requestTransform.transform(fullHttpRequest);
        HttpServletResponse response = new DefaultHttpServletResponse();
        try {
            servlet.service(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FullHttpResponse fullHttpResponse = responseTransform.transform(response);
        return fullHttpResponse;
    }

}
