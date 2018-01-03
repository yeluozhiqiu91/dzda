package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("TDictionaryDao")
public interface TDictionaryDao extends Closeable
{
    @ThriftService("TDictionaryDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getTDicCount")
        ListenableFuture<Long> getTDicCount(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TDictionary record
        );

        @ThriftMethod(value = "delTDic")
        ListenableFuture<Void> delTDic(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "getCategoryList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TDictionary>> getCategoryList(
            @ThriftField(value=1, name="dtype", requiredness=Requiredness.NONE) final String dtype
        );

        @ThriftMethod(value = "insertTDic")
        ListenableFuture<Void> insertTDic(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TDictionary record
        );

        @ThriftMethod(value = "updateTDic")
        ListenableFuture<Void> updateTDic(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TDictionary record
        );

        @ThriftMethod(value = "getTDicList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TDictionary>> getTDicList(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TDictionary record,
            @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "getTDicById")
        ListenableFuture<com.igool.rpc.db.model.thrift.TDictionary> getTDicById(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
        );
    }
    void close();


    @ThriftMethod(value = "getTDicCount")
    long getTDicCount(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TDictionary record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "delTDic")
    void delTDic(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getCategoryList")
    List<com.igool.rpc.db.model.thrift.TDictionary> getCategoryList(
        @ThriftField(value=1, name="dtype", requiredness=Requiredness.NONE) final String dtype
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "insertTDic")
    void insertTDic(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TDictionary record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updateTDic")
    void updateTDic(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TDictionary record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTDicList")
    List<com.igool.rpc.db.model.thrift.TDictionary> getTDicList(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TDictionary record,
        @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTDicById")
    com.igool.rpc.db.model.thrift.TDictionary getTDicById(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
    ) throws org.apache.thrift.TException;
}