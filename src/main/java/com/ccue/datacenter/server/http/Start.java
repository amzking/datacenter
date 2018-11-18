package com.ccue.datacenter.server.http;

public class Start {

    public static void main(String[] args) {
        HttpNioServer server = new HttpNioServer(8080);
        try {
            server.serve();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
