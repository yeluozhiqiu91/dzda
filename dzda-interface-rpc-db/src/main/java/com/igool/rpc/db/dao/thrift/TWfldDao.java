package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("TWfldDao")
public interface TWfldDao extends Closeable
{
    @ThriftService("TWfldDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getWfldCount")
        ListenableFuture<Long> getWfldCount(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfldMessage record
        );

        @ThriftMethod(value = "updWfld")
        ListenableFuture<Void> updWfld(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfldMessage record
        );

        @ThriftMethod(value = "delWfld")
        ListenableFuture<Void> delWfld(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id,
            @ThriftField(value=2, name="pid", requiredness=Requiredness.NONE) final String pid
        );

        @ThriftMethod(value = "getWfldList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TWfldMessage>> getWfldList(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfldMessage record,
            @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "getWfldById")
        ListenableFuture<com.igool.rpc.db.model.thrift.TWfldMessage> getWfldById(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id,
            @ThriftField(value=2, name="pid", requiredness=Requiredness.NONE) final String pid
        );

        @ThriftMethod(value = "insWfld")
        ListenableFuture<Void> insWfld(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfldMessage record
        );
    }
    void close();


    @ThriftMethod(value = "getWfldCount")
    long getWfldCount(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfldMessage record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updWfld")
    void updWfld(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfldMessage record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "delWfld")
    void delWfld(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id,
        @ThriftField(value=2, name="pid", requiredness=Requiredness.NONE) final String pid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getWfldList")
    List<com.igool.rpc.db.model.thrift.TWfldMessage> getWfldList(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfldMessage record,
        @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getWfldById")
    com.igool.rpc.db.model.thrift.TWfldMessage getWfldById(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id,
        @ThriftField(value=2, name="pid", requiredness=Requiredness.NONE) final String pid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "insWfld")
    void insWfld(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfldMessage record
    ) throws org.apache.thrift.TException;
}