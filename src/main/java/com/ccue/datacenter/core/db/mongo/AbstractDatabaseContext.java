package com.ccue.datacenter.core.db.mongo;

import com.ccue.datacenter.core.db.DatabaseContext;

public abstract class AbstractDatabaseContext implements DatabaseContext {

    private String configFile;

    protected abstract static class Loader<T extends Loader<T>> {

        private String configFile;

        public T configFile(String configFile) {
            configFile = configFile;
            return self();
        }

        abstract AbstractDatabaseContext load();

        protected abstract T self();

        protected String getConfigFile() {
            return configFile;
        }
    }

    AbstractDatabaseContext(Loader<?> loader) {
        configFile = loader.configFile;
    }

}
