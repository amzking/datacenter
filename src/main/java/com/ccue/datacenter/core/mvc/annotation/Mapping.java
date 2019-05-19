package com.ccue.datacenter.core.mvc.annotation;

import com.ccue.datacenter.core.server.http.request.HttpRequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Mapping {

    /**
     * 默认为根路径
     * 以/开头，若无则补全
     *
     * @return
     */
    String value() default "/";

    HttpRequestMethod method() default HttpRequestMethod.GET;
}
