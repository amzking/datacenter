package com.ccue.datacenter.core.server.http.response;

public interface HttpResponse extends Response {

    String getStatusLine();

    String getHeaders();

    String getBody();

}
