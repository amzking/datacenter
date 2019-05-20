package com.ccue.datacenter.core.mvc.http.response;

public interface HttpResponse extends Response {

    String getStatusLine();

    String getHeaders();

    String getBody();

}
