package com.ccue.datacenter.core.mvc.mapping;

import java.util.Map;

public class RouterBean {

    /**
     * dispatch 对象
     */
    private Object router;

    /**
     * dispatch 匹配模式,默认为 /
     */
    private String pattern;

    /**
     * dispatch 下各个mapping的匹配
     */
    private Map<String, MappingHandler> map;

}
