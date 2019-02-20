package com.ccue.datacenter.core.db.mongo;

import com.ccue.datacenter.core.db.DatabaseContext;

public abstract class AbstractDatabaseContext implements DatabaseContext {

    private final String userName;

    private final String password;

    private final String dbName;

    protected abstract static class Loader<T extends Loader<T>> {

        private String userName;

        private String password;

        private String dbName;

        private T userName(String userName) {
            userName = userName;
            return self();
        }

        private T password(String password) {
            password = password;
            return self();
        }

        private T dbName(String dbName) {
            dbName = dbName;
            return self();
        }
        abstract AbstractDatabaseContext load();
        protected abstract T self();
    }

    AbstractDatabaseContext(Loader<?>  loader) {
        userName = loader.userName;
        password = loader.password;
        dbName = loader.dbName;
    }

    public String getDBName() {
        return this.dbName;
    }

}
