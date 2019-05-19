package com.ccue.datacenter.core.mvc.transform;

public interface ITransform<T, K> {

    K transform(T t);

}
