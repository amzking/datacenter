package com.ccue.datacenter.core.server.http.mvc.transform;

import com.ccue.datacenter.core.mvc.transform.ITransform;
import io.netty.handler.codec.http.FullHttpRequest;

public interface NettyRequestTransform<T> extends ITransform<FullHttpRequest, T> {
}
