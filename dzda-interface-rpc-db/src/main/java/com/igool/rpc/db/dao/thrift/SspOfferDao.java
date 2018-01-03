package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("SspOfferDao")
public interface SspOfferDao extends Closeable
{
    @ThriftService("SspOfferDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "updateOfferRecord")
        ListenableFuture<Void> updateOfferRecord(
            @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
        );

        @ThriftMethod(value = "updateOfferRecordTwo")
        ListenableFuture<Void> updateOfferRecordTwo(
            @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
        );

        @ThriftMethod(value = "getListCountWFSH")
        ListenableFuture<Long> getListCountWFSH(
            @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
        );

        @ThriftMethod(value = "getListWFSH")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.SspOfferRecord>> getListWFSH(
            @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord,
            @ThriftField(value=2, name="startIndex", requiredness=Requiredness.NONE) final int startIndex,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "getRecordById")
        ListenableFuture<com.igool.rpc.db.model.thrift.SspOfferRecord> getRecordById(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
        );

        @ThriftMethod(value = "getListByRegionAndDate")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.SspOfferRecord>> getListByRegionAndDate(
            @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
        );

        @ThriftMethod(value = "getWatchOfferList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.SspOfferRecord>> getWatchOfferList(
            @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord,
            @ThriftField(value=2, name="startIndex", requiredness=Requiredness.NONE) final int startIndex,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "getWatchOfferListCount")
        ListenableFuture<Long> getWatchOfferListCount(
            @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
        );
    }
    void close();


    @ThriftMethod(value = "updateOfferRecord")
    void updateOfferRecord(
        @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updateOfferRecordTwo")
    void updateOfferRecordTwo(
        @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getListCountWFSH")
    long getListCountWFSH(
        @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getListWFSH")
    List<com.igool.rpc.db.model.thrift.SspOfferRecord> getListWFSH(
        @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord,
        @ThriftField(value=2, name="startIndex", requiredness=Requiredness.NONE) final int startIndex,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getRecordById")
    com.igool.rpc.db.model.thrift.SspOfferRecord getRecordById(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getListByRegionAndDate")
    List<com.igool.rpc.db.model.thrift.SspOfferRecord> getListByRegionAndDate(
        @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getWatchOfferList")
    List<com.igool.rpc.db.model.thrift.SspOfferRecord> getWatchOfferList(
        @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord,
        @ThriftField(value=2, name="startIndex", requiredness=Requiredness.NONE) final int startIndex,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getWatchOfferListCount")
    long getWatchOfferListCount(
        @ThriftField(value=1, name="sspOfferRecord", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.SspOfferRecord sspOfferRecord
    ) throws org.apache.thrift.TException;
}