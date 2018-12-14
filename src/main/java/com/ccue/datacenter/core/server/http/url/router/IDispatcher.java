package com.ccue.datacenter.core.server.http.url.router;

import com.ccue.datacenter.core.server.ServerContext;

public interface IDispatcher<T, K> {


    K route(T t);

    ServerContext getServerContext();

}
