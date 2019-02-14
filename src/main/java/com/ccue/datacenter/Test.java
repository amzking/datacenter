package com.ccue.datacenter;

import com.ccue.datacenter.core.db.mongo.MongoDBContext;
import com.ccue.datacenter.utils.properties.PropertyUtil;
import com.ccue.datacenter.utils.yaml.YamlUtil;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        List str = PropertyUtil.getInstance().getList("zk");
        System.out.println(str);

        //MongoDBContext context = new MongoDBContext.Builder().clusters(null).build();

        new YamlUtil.Reader().path("mongo.yaml").load();

    }
}
