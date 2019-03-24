package com.ccue.datacenter.core.server.http;

import com.ccue.datacenter.core.server.ServerContext;
import com.ccue.datacenter.core.server.http.mvc.dispatch.IDispatcher;

public interface HttpServerContext<K, T> extends ServerContext {

    IDispatcher<K, T>  getDispatcher();

}
