// Generated by swift-generator from dzda/statisticsservice.thrift

package com.igool.rpc.db.service.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;


@ThriftService("StatisticsService")
public interface StatisticsService extends Closeable
{
    @ThriftService("StatisticsService")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getStatistics")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Statistics>> getStatistics(
            @ThriftField(value=1, name="query", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Statistics query
        );

        @ThriftMethod(value = "addStatistics")
        ListenableFuture<Void> addStatistics(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Statistics record
        );

        @ThriftMethod(value = "getDeliveryStatisticsFilter")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.StatisticsFilter>> getDeliveryStatisticsFilter(
            @ThriftField(value=1, name="query", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Statistics query
        );

        @ThriftMethod(value = "getSignStatisticsFilter")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.StatisticsFilter>> getSignStatisticsFilter(
            @ThriftField(value=1, name="query", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Statistics query
        );

        @ThriftMethod(value = "getJjtj")
        ListenableFuture<com.igool.rpc.db.model.thrift.Jjtj> getJjtj(
            @ThriftField(value=1, name="query", requiredness=Requiredness.NONE) final List<com.igool.rpc.db.model.thrift.StatisticsFilter> query
        );
    }
    void close();


    @ThriftMethod(value = "getStatistics")
    List<com.igool.rpc.db.model.thrift.Statistics> getStatistics(
        @ThriftField(value=1, name="query", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Statistics query
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "addStatistics")
    void addStatistics(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Statistics record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getDeliveryStatisticsFilter")
    List<com.igool.rpc.db.model.thrift.StatisticsFilter> getDeliveryStatisticsFilter(
        @ThriftField(value=1, name="query", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Statistics query
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getSignStatisticsFilter")
    List<com.igool.rpc.db.model.thrift.StatisticsFilter> getSignStatisticsFilter(
        @ThriftField(value=1, name="query", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Statistics query
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getJjtj")
    com.igool.rpc.db.model.thrift.Jjtj getJjtj(
        @ThriftField(value=1, name="query", requiredness=Requiredness.NONE) final List<com.igool.rpc.db.model.thrift.StatisticsFilter> query
    ) throws org.apache.thrift.TException;
}