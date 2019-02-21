package com.ccue.datacenter.core.db.mongo;


import com.ccue.datacenter.utils.yaml.YamlUtil;

import java.util.List;

/**
 * @description: mongo上下文
 * @since: 2019-01-07
 */
public class MongoDBContext extends AbstractDatabaseContext {

    private final MongoDBConfig config;

    /**
     * @description: 加载配置上下文
     * @since: 2019-02-20
     * @param
     * @return: void
     */
    @Override
    public void init() {
        MongoDBClinetHolder.initContext();
    }

    public static class Loader extends AbstractDatabaseContext.Loader<Loader> {
        private MongoDBConfig config;

        private String DEFALT_PROPERTIES = "mongo.yaml";

        /**
         * @description:若不提供默认配置路径，则加载默认文件,确保最后调用
         * @since: 2019-02-20
         * @param
         * @return: com.ccue.datacenter.core.db.mongo.MongoDBContext.Builder
         */
        public MongoDBContext load() {
            String configFile = this.getConfigFile();
            if (configFile == null) {
                configFile = DEFALT_PROPERTIES;
            }
            MongoDBConfig config = new YamlUtil.Reader().path(configFile).load().readAs(MongoDBConfig.class);
            this.config = config;
            return new MongoDBContext(this);
        }
        @Override
        protected Loader self() {
            return this;
        }
    }

    MongoDBContext(Loader loader) {
        super(loader);
        config = loader.config;
    }

    public String getUserName() {
        return config.getUserName();
    }


    public String getPassword() {
        return config.getPassword();
    }

    public String getDbName() {
        return config.getDbName();
    }

    public List<MongoRepica> getServers() {
        return config.getServers();
    }

    public Integer getMaintenanceFrequency() {
        return config.getMaintenanceFrequency();
    }

    public Integer getMaintenanceInitialDelay() {
        return config.getMaintenanceInitialDelay();
    }

    public Integer getMaxConnectionIdleTime() {
        return config.getMaxConnectionIdleTime();
    }

    public Integer getMaxConnectionLifeTime() {
        return config.getMaxConnectionLifeTime();
    }

    public Integer getMaxSize() {
        return config.getMaxSize();
    }

    public Integer getMinSize() {
        return config.getMinSize();
    }

    public Integer getMaxWaitQueueSize() {
        return config.getMaxWaitQueueSize();
    }

    public Integer getMaxWaitTime() {
        return config.getMaxWaitTime();
    }

}
