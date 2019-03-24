package com.ccue.datacenter.core.server.http.mvc.transform;

import io.netty.handler.codec.http.FullHttpResponse;

public interface NettyResponseTransform<T> extends ITransform<T, FullHttpResponse> {

}
