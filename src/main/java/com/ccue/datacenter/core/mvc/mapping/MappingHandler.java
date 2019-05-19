package com.ccue.datacenter.core.mvc.mapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 调用方法的包装类
 */
public class MappingHandler {

    private RouterBean routerBean;

    private String pattern;

    private Method method;

    public void invoke(String params) throws InvocationTargetException, IllegalAccessException {
        Object invoke = method.invoke("");
    }

}
