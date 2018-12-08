package com.ccue.datacenter.core.server.http;

import com.ccue.datacenter.core.server.ServerContext;
import com.ccue.datacenter.core.server.http.url.router.UrlRouter;

public interface HttpServerContext extends ServerContext {


    UrlRouter getUrlRouter();


}
