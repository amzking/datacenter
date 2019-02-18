package com.ccue.datacenter.core.db.mongo;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class MongoDBClinetHolder {

    private static Logger logger = LogManager.getLogger(MongoDBClinetHolder.class);


    /**
     * @description: 在获取前应该先初始化
     * @since: 2019-02-18
     */
    private MongoClient client = null;

    private byte[] lock = new byte[8];

    private static MongoCredential credential = null;
    private static CodecRegistry pojoCodecRegistry = null;
    private static Block<ClusterSettings.Builder> hosts = null;
    private static MongoClientSettings settings = null;

    public static void initContext() {
        // 获取用户名密码
        credential = MongoCredential.createCredential(null, null, null);
        pojoCodecRegistry = CodecRegistries.fromRegistries(null,
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        hosts = builder ->
                builder.hosts(Arrays.asList(new ServerAddress("host1", 27017)));
        settings = MongoClientSettings.builder()
                .credential(credential)
                .codecRegistry(pojoCodecRegistry)
                .applyToConnectionPoolSettings(new Block<ConnectionPoolSettings.Builder>() {
                    @Override
                    public void apply(ConnectionPoolSettings.Builder builder) {
                        builder.addConnectionPoolListener(new MongoConnectionListener())
                                .maintenanceFrequency(555, TimeUnit.MILLISECONDS)
                                .maintenanceInitialDelay(555, TimeUnit.MILLISECONDS)
                                .maxConnectionIdleTime(555, TimeUnit.MILLISECONDS)
                                .maxConnectionLifeTime(555, TimeUnit.MILLISECONDS)
                                .maxSize(555)
                                .minSize(10)
                                .maxWaitQueueSize(555)
                                .maxWaitTime(555, TimeUnit.MILLISECONDS)
                                .build();
                    }
                })
                .applyToClusterSettings(hosts)
                .build();
    }



    /**
     * @description: 获取mongodbclient
     * @since: 2019-02-18
     * @param
     * @return: com.mongodb.client.MongoClient
     */
    public MongoClient getClient() {
        if (client == null) {
            synchronized (lock) {
                if (client == null) {
                    initContext();
                    client = MongoClients.create(settings);
                }
            }
        }
        return client;
    }

    /**
     * mongoDB 连接池监听
     */
    private static class MongoConnectionListener extends ConnectionPoolListenerAdapter {

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

}
