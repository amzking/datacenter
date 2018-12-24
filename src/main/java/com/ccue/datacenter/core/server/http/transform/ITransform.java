package com.ccue.datacenter.core.server.http.transform;

public interface ITransform<T, K> {

    K transform(T t);

}
