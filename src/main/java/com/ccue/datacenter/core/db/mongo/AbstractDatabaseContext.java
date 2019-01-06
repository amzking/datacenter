package com.ccue.datacenter.core.db.mongo;

import com.ccue.datacenter.core.db.DatabaseContext;

public abstract class AbstractDatabaseContext implements DatabaseContext {

    final String userName;

    final String password;

    final String dbName;

    abstract static class Builder<T extends Builder<T>> {

        String userName;

        String password;

        String dbName;

        public T userName(String userName) {
            userName = userName;
            return self();
        }

        public T password(String password) {
            password = password;
            return self();
        }

        public T dbName(String dbName) {
            dbName = dbName;
            return self();
        }
        abstract AbstractDatabaseContext build();
        protected abstract T self();
    }

    AbstractDatabaseContext(Builder<?>  builder) {
        userName = builder.userName;
        password = builder.password;
        dbName = builder.dbName;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getDBName() {
        return this.dbName;
    }
}
