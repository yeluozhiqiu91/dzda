package com.igool.thrift.rpc;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.NettyServerConfigBuilder;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;

//import org.junit.Assert;

/**
 * Created by igool on 2017/6/27.
 */
public class ServerCreator {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService taskWorkerExecutor;
    private ThriftServer server;
    private ExecutorService bossExecutor;
    private ExecutorService ioWorkerExecutor;

    public ThriftServer getServer() {
        return server;
    }

    public ServerCreator invoke(List serviceList, int port, ServerConfig serverConfig) {
        ThriftServiceProcessor processor = new ThriftServiceProcessor(
                new ThriftCodecManager(),
                ImmutableList.<ThriftEventHandler>of(),
                serviceList
        );

        taskWorkerExecutor = newFixedThreadPool(1);

        ThriftServerDef serverDef = ThriftServerDef.newBuilder()
                .listen(port)
                .withProcessor(processor)
                .using(taskWorkerExecutor)
                .limitFrameSizeTo(serverConfig.getMaxFrameSize()/*67108864*5*/)
                .build();
        logger.info("ThriftServerDef config {}",serverDef.getMaxFrameSize());
        bossExecutor = newCachedThreadPool();
        ioWorkerExecutor = newCachedThreadPool();

        NettyServerConfigBuilder configBuilder =  NettyServerConfig.newBuilder()
                .setBossThreadExecutor(bossExecutor)
                .setWorkerThreadExecutor(ioWorkerExecutor);
        configBuilder.getServerSocketChannelConfig().setReceiveBufferSize(serverConfig.getReceiveBufferSize()/*1024*20*/);
        NettyServerConfig nettyServerConfig = configBuilder.build();
        //serverConfig.getBootstrapOptions().put("receiveBufferSize",1024*20);
        logger.info("NettyServerConfig config {}",nettyServerConfig.getBootstrapOptions());
        //个性化设置

        server = new ThriftServer(nettyServerConfig, serverDef);
        return this;
    }

    public void stop() {
        server.close();
    }
}

