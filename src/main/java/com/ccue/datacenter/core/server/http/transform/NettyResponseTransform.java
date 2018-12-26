package com.ccue.datacenter.core.server.http.transform;

import io.netty.handler.codec.http.FullHttpResponse;
import javax.servlet.http.HttpServletResponse;

public interface NettyResponseTransform<T> extends ITransform<T, FullHttpResponse> {

}
