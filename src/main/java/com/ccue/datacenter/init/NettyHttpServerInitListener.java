package com.ccue.datacenter.init;

import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyHttpServerInitListener implements GenericFutureListener<ChannelFuture> {


    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        //initContext();
    }
}
