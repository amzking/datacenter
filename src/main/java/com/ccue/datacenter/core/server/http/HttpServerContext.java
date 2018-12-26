package com.ccue.datacenter.core.server.http;

import com.ccue.datacenter.core.server.ServerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpServerContext extends ServerContext<FullHttpRequest, FullHttpResponse> {


}
