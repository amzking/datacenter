package com.ccue.datacenter.core.server.http.request;

import io.netty.handler.codec.http.FullHttpRequest;

public abstract class AbstractHttpIntercepter implements HttpIntercepter<FullHttpRequest> {

    private AbstractHttpIntercepter next;
    private AbstractHttpIntercepter prev;
    public HttpIntercepterManager holder;

    public AbstractHttpIntercepter(HttpIntercepterManager holder) {
        this.holder = holder;
    }

    @Override
    public String getIntercepterName() {
        return this.getClass().getName();
    }

    public void setNext(AbstractHttpIntercepter next) {
        this.next = next;
    }

    public AbstractHttpIntercepter next() {
        return this.next;
    }

    public void setPrev(AbstractHttpIntercepter prev) {
        this.prev = prev;
    }

    public AbstractHttpIntercepter prev() {
        return this.prev;
    }


}
