package com.ccue.datacenter.business.router;

import com.ccue.datacenter.core.server.http.request.HttpRequestMethod;
import com.ccue.datacenter.core.server.http.response.HttpResponse;
import com.ccue.datacenter.core.mapping.Mapping;
import com.ccue.datacenter.core.mapping.Router;

@Router
@Mapping("/test")
public class TestRouter {

    /**
     * 很多时候，并不能确定返回什么，有可能返回的是数据
     * 1. 原生数据 RAW
     * 2. JSON数据
     * 3. 视图（需要将数据进行渲染）
     * @return
     */
    @Mapping(value="/t", method=HttpRequestMethod.GET)
    public Object testMapping(String data) {

        System.out.println("Test mapping");
        return null;
    }
}
