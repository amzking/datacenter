package com.ccue.datacenter.core.db.mongo;

/**
 * @author joking@aliyun.com
 * @description
 * @date 2019-02-18
 */
public class MongoRepica {
    private String host;
    private Integer port;

    public MongoRepica(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
