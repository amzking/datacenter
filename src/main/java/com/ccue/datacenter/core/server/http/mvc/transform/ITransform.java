package com.ccue.datacenter.core.server.http.mvc.transform;

public interface ITransform<T, K> {

    K transform(T t);

}
