package com.ccue.datacenter.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 兼容tomcat等servlet容器
 */
public class HttpServletInitListener extends HttpServerContextLoader implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        initContext();

    }

    public void contextDestroyed(ServletContextEvent event) {

    }

}
