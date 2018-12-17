package com.ccue.datacenter.init;

import com.ccue.datacenter.core.event.EventListener;
import com.ccue.datacenter.core.server.http.HttpServerContext;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 服务初始化类，用于HttpServer
 */
public class HttpServerContextLoader {

    /**
     * 事件监听器添加有序
     */
    public Map<String, EventListener> map = new LinkedHashMap<>();

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

    public void registListener(EventListener listener) {
        map.put(listener.getClass().getName(), listener);
    }

}
