package com.igool.thrift.rpc;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.nifty.client.NiftyClientChannel;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.util.concurrent.ListenableFuture;
import com.igool.thrift.rpc.zookeeper.ThriftServerAddressProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

/**
 * Created by igool on 2017/8/1.
 * 客户端代理简单版
 */
public class ThriftClientProxy implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ThriftServerAddressProvider serverAddressProvider;
    private ThriftClientManager clientManager;
    private FramedClientConnector framedClientConnector;

    public void setServerAddressProvider(ThriftServerAddressProvider serverAddressProvider) {
        this.serverAddressProvider = serverAddressProvider;
    }

    public void setClientManager(ThriftClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public <T> T getService(Class<T>  objectClass)  {
        try {
            return (T) clientManager.createClient(framedClientConnector  ,objectClass).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("InterruptedException",e);
        } catch (ExecutionException e) {
            e.printStackTrace();
            logger.error("ExecutionException",e);
        }
        return (T) objectClass;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        InetSocketAddress address = serverAddressProvider.selector();
        if(address==null){
            throw new ThriftException("No provider available for remote service");
        }
        framedClientConnector =  new FramedClientConnector(new InetSocketAddress(address.getHostName(), address.getPort()));

    }
}
