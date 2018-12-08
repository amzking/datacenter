package com.ccue.datacenter.core.server.http.url.router;

import com.ccue.datacenter.core.server.http.request.HttpRequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Mapping {

    /**
     * 默认为根路径
     * @return
     */
    String value() default "/";

    HttpRequestMethod[] method() default {};
}
