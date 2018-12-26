package com.ccue.datacenter.core.server.http.transform;

import io.netty.handler.codec.http.FullHttpRequest;

public interface NettyRequestTransform<T> extends ITransform<FullHttpRequest, T> {
}
