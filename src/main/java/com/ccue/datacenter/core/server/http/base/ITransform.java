package com.ccue.datacenter.core.server.http.base;

public interface ITransform<T, K> {

    K transform(T t);

}
