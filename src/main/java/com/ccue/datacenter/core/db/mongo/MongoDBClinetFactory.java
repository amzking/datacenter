package com.ccue.datacenter.core.db.mongo;

import com.ccue.datacenter.core.db.DatabaseContext;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class MongoDBClinetFactory {


    private DatabaseContext context;

    public MongoClient getClient() {
        MongoCredential credential = MongoCredential.createCredential(context.getUserName(), null, context.getPassword().toCharArray());
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(null,
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        Block<ClusterSettings.Builder> hosts = builder ->
                builder.hosts(Arrays.asList(new ServerAddress("host1", 27017)));

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .codecRegistry(pojoCodecRegistry)
                .applyToClusterSettings(hosts)
                .build();

        MongoClient client = MongoClients.create(settings);

        return client;
    }
}
