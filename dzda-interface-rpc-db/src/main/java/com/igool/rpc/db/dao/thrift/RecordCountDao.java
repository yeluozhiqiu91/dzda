package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("RecordCountDao")
public interface RecordCountDao extends Closeable
{
    @ThriftService("RecordCountDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getTotalRegionCountList")
        ListenableFuture<Long> getTotalRegionCountList(
            @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RegionCount arg0
        );

        @ThriftMethod(value = "getTotalUserCountList")
        ListenableFuture<Long> getTotalUserCountList(
            @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserCount arg0
        );

        @ThriftMethod(value = "getTotalRewardCountList")
        ListenableFuture<Long> getTotalRewardCountList(
            @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RewardCount arg0
        );

        @ThriftMethod(value = "getRegionCountList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.RegionCount>> getRegionCountList(
            @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RegionCount arg0,
            @ThriftField(value=2, name="arg1", requiredness=Requiredness.NONE) final int arg1,
            @ThriftField(value=3, name="arg2", requiredness=Requiredness.NONE) final int arg2
        );

        @ThriftMethod(value = "getUserCountList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.UserCount>> getUserCountList(
            @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserCount arg0,
            @ThriftField(value=2, name="arg1", requiredness=Requiredness.NONE) final int arg1,
            @ThriftField(value=3, name="arg2", requiredness=Requiredness.NONE) final int arg2
        );

        @ThriftMethod(value = "getRewardCountList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.RewardCount>> getRewardCountList(
            @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RewardCount arg0,
            @ThriftField(value=2, name="arg1", requiredness=Requiredness.NONE) final int arg1,
            @ThriftField(value=3, name="arg2", requiredness=Requiredness.NONE) final int arg2
        );

        @ThriftMethod(value = "getAllRewardCountList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.RewardCount>> getAllRewardCountList(
            @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RewardCount arg0
        );
    }
    void close();


    @ThriftMethod(value = "getTotalRegionCountList")
    long getTotalRegionCountList(
        @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RegionCount arg0
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTotalUserCountList")
    long getTotalUserCountList(
        @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserCount arg0
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTotalRewardCountList")
    long getTotalRewardCountList(
        @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RewardCount arg0
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getRegionCountList")
    List<com.igool.rpc.db.model.thrift.RegionCount> getRegionCountList(
        @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RegionCount arg0,
        @ThriftField(value=2, name="arg1", requiredness=Requiredness.NONE) final int arg1,
        @ThriftField(value=3, name="arg2", requiredness=Requiredness.NONE) final int arg2
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getUserCountList")
    List<com.igool.rpc.db.model.thrift.UserCount> getUserCountList(
        @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserCount arg0,
        @ThriftField(value=2, name="arg1", requiredness=Requiredness.NONE) final int arg1,
        @ThriftField(value=3, name="arg2", requiredness=Requiredness.NONE) final int arg2
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getRewardCountList")
    List<com.igool.rpc.db.model.thrift.RewardCount> getRewardCountList(
        @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RewardCount arg0,
        @ThriftField(value=2, name="arg1", requiredness=Requiredness.NONE) final int arg1,
        @ThriftField(value=3, name="arg2", requiredness=Requiredness.NONE) final int arg2
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getAllRewardCountList")
    List<com.igool.rpc.db.model.thrift.RewardCount> getAllRewardCountList(
        @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RewardCount arg0
    ) throws org.apache.thrift.TException;
}