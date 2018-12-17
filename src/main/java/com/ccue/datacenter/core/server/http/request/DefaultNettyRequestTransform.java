package com.ccue.datacenter.core.server.http.request;

import io.netty.handler.codec.http.FullHttpRequest;

public class DefaultNettyRequestTransform implements NettyRequestTransform<FullHttpRequest> {
    @Override
    public FullHttpRequest transform(FullHttpRequest defaultFullHttpRequest) {
        return null;
    }
}
