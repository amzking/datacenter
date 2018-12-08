package com.ccue.datacenter.core.server.http.url.router;

import com.ccue.datacenter.core.server.http.request.HttpRequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Router {

    String value();

    HttpRequestMethod[] method() default {};

}
