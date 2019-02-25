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

import java.util.ArrayList;
import java.util.List;
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
    private static MongoClient client = null;

    private static MongoClientSettings settings;

    protected static void initContext() {
        // 获取用户名密码
        MongoDBContext context = new MongoDBContext.Loader().load();

        MongoCredential credential = MongoCredential.createCredential(context.getUserName(), context.getDbName(), context.getPassword().toCharArray());
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(null,
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        List<MongoRepica> servers = context.getServers() == null ? new ArrayList<>(1) : context.getServers();
        List<ServerAddress> serverAddresses = new ArrayList<>();
        if (servers.size() == 0) {
            servers.add(new MongoRepica("127.0.0.1", 27017));
        }
        for (MongoRepica repica : servers) {
            serverAddresses.add(new ServerAddress(repica.getHost(), repica.getPort()));
        }
        Block<ClusterSettings.Builder> hosts = builder ->
                builder.hosts(serverAddresses);
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
                                .minSize(context.getMinSize())
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
    public static MongoClient getClient() {
        if (client == null) {
            synchronized (MongoDBClinetHolder.class) {
                if (client == null) {
                    initContext();
                    client = MongoClients.create(settings);
                }
            }
        }
        return client;
    }

}