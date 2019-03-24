package com.ccue.datacenter.core.server.http.mvc.dispatch;

public interface IDispatcher<T, K> {

    K dispatch(T t);

}
