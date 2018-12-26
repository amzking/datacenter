package com.ccue.datacenter.core.mapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
