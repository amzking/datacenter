package com.ccue.datacenter.core.server.http;

import com.ccue.datacenter.core.mvc.dispatch.IDispatcher;
import com.ccue.datacenter.core.server.ServerContext;

public interface HttpServerContext<K, T> extends ServerContext {

    IDispatcher<K, T> getDispatcher();

}
