package com.ccue.datacenter.init;

import com.ccue.datacenter.core.server.http.HttpServerContext;

/**
 * 服务初始化类，用于HttpServer
 */
public class HttpServerContextLoader {

    private HttpServerContext context;

    protected void initContext() {
        // 加载扫描 url注解
        if (context == null) {

        }
        this.initUrlMapping(context);
        this.initResolver(context);
    }



    private void initUrlMapping(HttpServerContext context) {

    }

    private void initResolver(HttpServerContext context) {
    }



}
