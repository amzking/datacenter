package com.ccue.datacenter.core.server.http.transform;

import io.netty.handler.codec.http.FullHttpResponse;

import javax.servlet.http.HttpServletResponse;

public class HttpServletResponseTransform implements NettyResponseTransform<HttpServletResponse> {
    @Override
    public FullHttpResponse transform(HttpServletResponse httpServletResponse) {
        return null;
    }
}
