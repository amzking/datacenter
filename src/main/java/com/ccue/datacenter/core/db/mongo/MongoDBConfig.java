package com.ccue.datacenter.core.db.mongo;

import java.util.List;

/**
 * @author joking@aliyun.com
 * @description 此类对应moongo config yaml
 * @date 2019-02-18
 */
public class MongoDBConfig {

    private String userName;

    private String password;

    private String dbName;

    private List<MongoRepica> servers;

    private Integer maintenanceFrequency;

    private Integer maintenanceInitialDelay;

    private Integer maxConnectionIdleTime;

    private Integer maxConnectionLifeTime;

    private Integer maxSize;

    private Integer minSize;

    private Integer maxWaitQueueSize;

    private Integer maxWaitTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<MongoRepica> getServers() {
        return servers;
    }

    public void setServers(List<MongoRepica> servers) {
        this.servers = servers;
    }

    public Integer getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    public void setMaintenanceFrequency(Integer maintenanceFrequency) {
        this.maintenanceFrequency = maintenanceFrequency;
    }

    public Integer getMaintenanceInitialDelay() {
        return maintenanceInitialDelay;
    }

    public void setMaintenanceInitialDelay(Integer maintenanceInitialDelay) {
        this.maintenanceInitialDelay = maintenanceInitialDelay;
    }

    public Integer getMaxConnectionIdleTime() {
        return maxConnectionIdleTime;
    }

    public void setMaxConnectionIdleTime(Integer maxConnectionIdleTime) {
        this.maxConnectionIdleTime = maxConnectionIdleTime;
    }

    public Integer getMaxConnectionLifeTime() {
        return maxConnectionLifeTime;
    }

    public void setMaxConnectionLifeTime(Integer maxConnectionLifeTime) {
        this.maxConnectionLifeTime = maxConnectionLifeTime;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxWaitQueueSize() {
        return maxWaitQueueSize;
    }

    public void setMaxWaitQueueSize(Integer maxWaitQueueSize) {
        this.maxWaitQueueSize = maxWaitQueueSize;
    }

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }
}
