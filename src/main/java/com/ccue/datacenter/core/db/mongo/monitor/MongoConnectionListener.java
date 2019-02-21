package com.ccue.datacenter.core.db.mongo.monitor;

import com.mongodb.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author joking@aliyun.com
 * @description mongo连接池监听
 * @date 2019-02-21
 */
public class MongoConnectionListener extends ConnectionPoolListenerAdapter {

    private static Logger logger = LogManager.getLogger(MongoConnectionListener.class);

    @Override
    public void connectionCheckedOut(ConnectionCheckedOutEvent connectionCheckedOutEvent) {
        logger.debug("get a connection");
    }

    @Override
    public void connectionCheckedIn(ConnectionCheckedInEvent connectionCheckedInEvent) {
        logger.debug("return a connection");
    }

    @Override
    public void waitQueueEntered(ConnectionPoolWaitQueueEnteredEvent connectionPoolWaitQueueEnteredEvent) {
        logger.debug("放入等待队列中，等待新连接。。。。。。。。。。。。。。。。。。。。。。");
    }

    @Override
    public void waitQueueExited(ConnectionPoolWaitQueueExitedEvent connectionPoolWaitQueueExitedEvent) {
        logger.debug("从等待队列中移除");
    }

    @Override
    public void connectionAdded(ConnectionAddedEvent connectionAddedEvent) {

    }

    @Override
    public void connectionRemoved(ConnectionRemovedEvent connectionRemovedEvent) {

    }
}


