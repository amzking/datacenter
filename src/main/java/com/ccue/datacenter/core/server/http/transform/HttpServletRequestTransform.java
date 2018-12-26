package com.ccue.datacenter.core.server.http.transform;

import io.netty.handler.codec.http.FullHttpRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * 类型转换
 */
public class HttpServletRequestTransform implements NettyRequestTransform<HttpServletRequest> {


    @Override
    public HttpServletRequest transform(FullHttpRequest defaultFullHttpRequest) {
        return null;
    }
}
