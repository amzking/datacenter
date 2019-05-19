package com.ccue.datacenter.core.mvc.dispatch;

public interface IDispatcher<T, K> {

    K dispatch(T t);

}
