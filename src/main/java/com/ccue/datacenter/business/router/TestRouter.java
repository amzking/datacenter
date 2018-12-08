package com.ccue.datacenter.business.router;

import com.ccue.datacenter.core.server.http.request.HttpRequestMethod;
import com.ccue.datacenter.core.server.http.response.HttpResponse;
import com.ccue.datacenter.core.server.http.url.router.Mapping;
import com.ccue.datacenter.core.server.http.url.router.Router;

@Router("/test")
public class TestRouter {

    @Mapping(value="/t", method=HttpRequestMethod.GET)
    public HttpResponse testMapping() {

        System.out.println("Test mapping");
        return null;
    }
}
