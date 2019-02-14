package com.ccue.datacenter.core.db.mongo;

import com.ccue.datacenter.core.db.DatabaseContext;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class MongoDBClinetHolder {

    private static Logger logger = LogManager.getLogger(MongoDBClinetHolder.class);

    private final DatabaseContext context;


    private MongoDBClinetHolder(DatabaseContext context) {
        this.context = context;
    }

    private static MongoCredential credential = MongoCredential.createCredential(null, null, null);

    private static CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(null,
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private static Block<ClusterSettings.Builder> hosts = builder ->
            builder.hosts(Arrays.asList(new ServerAddress("host1", 27017)));

    private static MongoClientSettings settings = MongoClientSettings.builder()
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

    private static MongoClient client = MongoClients.create(settings);

    public MongoClient getClient() {
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
