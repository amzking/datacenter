package com.ccue.datacenter.core.mvc.http;

public interface HttpIntercepter<T> {

    String getIntercepterName();

    boolean accept(T t);

}
