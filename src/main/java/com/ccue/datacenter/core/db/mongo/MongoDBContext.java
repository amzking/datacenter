package com.ccue.datacenter.core.db.mongo;

import com.ccue.datacenter.core.db.DatabaseContext;

public class MongoDBContext extends AbstractDatabaseContext {


    private final boolean cluster;

    public static class Builder extends AbstractDatabaseContext.Builder<Builder> {
        private boolean cluster = false;
        public Builder cluster() {
            cluster = true;
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
        cluster = builder.cluster;
    }

}
