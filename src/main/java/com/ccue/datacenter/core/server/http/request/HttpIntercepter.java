package com.ccue.datacenter.core.server.http.request;

public interface HttpIntercepter<T> {

    String getIntercepterName();

    boolean accept(T t);

}
