package com.ccue.datacenter.core.db.mongo;

import com.ccue.datacenter.core.db.mongo.monitor.MongoConnectionListener;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

/**
 * @description: 此类应该是一个单例类
 * @since: 2019-02-20
 * @param
 * @return:
 */
public class MongoDBClinetHolder {

    private static Logger logger = LogManager.getLogger(MongoDBClinetHolder.class);

    /**
     * @description: 在获取前应该先初始化
     * @since: 2019-02-18
     */
    private MongoClient client = null;

    private byte[] lock = new byte[8];

    private static MongoClientSettings settings;

    public static void initContext() {
        // 获取用户名密码
        MongoDBContext context = new MongoDBContext.Loader().load();

        MongoCredential credential = MongoCredential.createCredential(context.getUserName(), context.getDbName(), context.getPassword().toCharArray());
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(null,
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        Block<ClusterSettings.Builder> hosts = builder ->
                builder.hosts(Arrays.asList(new ServerAddress("host1", 27017)));
        settings = MongoClientSettings.builder()
                .credential(credential)
                .codecRegistry(pojoCodecRegistry)
                .applyToConnectionPoolSettings(new Block<ConnectionPoolSettings.Builder>() {
                    @Override
                    public void apply(ConnectionPoolSettings.Builder builder) {
                        builder.addConnectionPoolListener(new MongoConnectionListener())
                                .maintenanceFrequency(context.getMaintenanceFrequency(), TimeUnit.MILLISECONDS)
                                .maintenanceInitialDelay(context.getMaintenanceInitialDelay(), TimeUnit.MILLISECONDS)
                                .maxConnectionIdleTime(context.getMaxConnectionIdleTime(), TimeUnit.MILLISECONDS)
                                .maxConnectionLifeTime(context.getMaxConnectionLifeTime(), TimeUnit.MILLISECONDS)
                                .maxSize(context.getMaxSize())
                                .maxWaitQueueSize(context.getMaxWaitQueueSize())
                                .maxWaitTime(context.getMaxWaitTime(), TimeUnit.MILLISECONDS)
                                .build();
                    }
                })
                .applyToClusterSettings(hosts)
                .build();
    }


    /**
     * @param
     * @description: 获取mongodbclient
     * @since: 2019-02-18
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

}