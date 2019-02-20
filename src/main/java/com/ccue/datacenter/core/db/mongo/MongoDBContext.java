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

    public static class Builder extends AbstractDatabaseContext.Builder<Builder> {
        private MongoDBConfig config;

        private String DEFALT_PROPERTIES = "mongo.yaml";

        /**
         * @description:若不提供默认配置路径，则加载默认文件
         * @since: 2019-02-20
         * @param configFile
         * @return: com.ccue.datacenter.core.db.mongo.MongoDBContext.Builder
         */
        public Builder loadConfig(String configFile) {
            if (configFile == null) {
                configFile = DEFALT_PROPERTIES;
            }
            MongoDBConfig config = new YamlUtil.Reader().path("mongo.yaml").load().readAs(MongoDBConfig.class);
            this.config = config;
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
        config = builder.config;
    }


}
