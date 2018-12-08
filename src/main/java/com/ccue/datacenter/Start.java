package com.ccue.datacenter;

import com.ccue.datacenter.core.server.http.HttpNioServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Start {

    private static Logger logger = LogManager.getLogger(Start.class);

    public static void main(String[] args) {
        logger.info("datacenter is Starting......................");
        HttpNioServer server = new HttpNioServer(8080);
        try {
            server.serve();
        } catch (InterruptedException e) {
            logger.error("datacenter failed......................");
            e.printStackTrace();
        }
    }
}
