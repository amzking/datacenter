package com.ccue.datacenter.core.db.mongo;


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


}
