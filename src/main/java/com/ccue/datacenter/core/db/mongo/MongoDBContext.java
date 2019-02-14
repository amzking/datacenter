package com.ccue.datacenter.core.db.mongo;

import com.ccue.datacenter.core.db.DatabaseContext;

import java.util.List;

/**
 * @description: mongo上下文
 * @since: 2019-01-07
 */
public class MongoDBContext extends AbstractDatabaseContext {

    private final List<MongoRepica> clusters;

    public static class Builder extends AbstractDatabaseContext.Builder<Builder> {
        private List<MongoRepica> clusters;
        public Builder clusters(List<MongoRepica> clusters) {
            this.clusters = clusters;
            return this;
        }
        public MongoDBContext build() {
            return new MongoDBContext(this);
        }
        @Override
        protected Builder self() {
            return this;
        }
    }

    MongoDBContext(Builder builder) {
        super(builder);
        clusters = builder.clusters;
    }

    public class MongoRepica {
        private String host;
        private String port;

        MongoRepica(String host, String port) {
            this.host = host;
            this.port = port;
        }
    }

}
