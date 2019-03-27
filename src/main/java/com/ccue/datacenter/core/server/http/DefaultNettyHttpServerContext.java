package com.ccue.datacenter.core.server.http;

import com.ccue.datacenter.core.server.http.mvc.dispatch.IDispatcher;
import com.ccue.datacenter.core.server.http.mvc.dispatch.NettyHttpDispatcher;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应该包含MVC注解的类，方法
 */
public class DefaultNettyHttpServerContext implements HttpServerContext<FullHttpRequest, FullHttpResponse> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultNettyHttpServerContext.class);

    private IDispatcher<FullHttpRequest, FullHttpResponse> dispatcher;

    /**
     * @description: 加载server 相关的配置文件
     * @since: 2019-03-24
     * @param null
     * @return:
     */
    private ContextLoader loader;

    public DefaultNettyHttpServerContext() {
        dispatcher = new NettyHttpDispatcher();
    }

    public DefaultNettyHttpServerContext(IDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * @param
     * @description: 具体的上下文初始化工作
     * @since: 2019-03-24
     * @return: void
     */
    @Override
    public void init() {
        logger.debug("DefaultNettyHttpServerContext 初始化");
        if (dispatcher == null) {
            dispatcher = new NettyHttpDispatcher();
        }
        // loader.load();
        logger.debug("使用的IDispatcher 为" + dispatcher.getClass().getName());
    }


    @Override
    public IDispatcher<FullHttpRequest, FullHttpResponse> getDispatcher() {
        return dispatcher;
    }
}
