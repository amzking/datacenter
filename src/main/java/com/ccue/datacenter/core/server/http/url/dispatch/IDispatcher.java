package com.ccue.datacenter.core.server.http.url.dispatch;

public interface IDispatcher<T, K> {

    K dispatch(T t);

    void initServerContext();

}
