package com.ccue.datacenter.core.event;

public interface EventListener<T> {

    void listen(T t);
}
