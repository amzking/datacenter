package com.ccue.datacenter.core.server.http.request;

import com.ccue.datacenter.core.server.http.base.ITransform;
import io.netty.handler.codec.http.FullHttpRequest;

public interface NettyRequestTransform<T> extends ITransform<FullHttpRequest, T> {
    @Override
    public T transform(FullHttpRequest defaultFullHttpRequest);
}
