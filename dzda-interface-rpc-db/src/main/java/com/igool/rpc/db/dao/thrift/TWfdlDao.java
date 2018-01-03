package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("TWfdlDao")
public interface TWfdlDao extends Closeable
{
    @ThriftService("TWfdlDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getMessById")
        ListenableFuture<com.igool.rpc.db.model.thrift.TWfdlMessage> getMessById(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "delMes")
        ListenableFuture<Void> delMes(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "queryRecordDlld")
        ListenableFuture<com.igool.rpc.db.model.thrift.RecordDlld> queryRecordDlld(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final long id
        );

        @ThriftMethod(value = "saveRecordDlld")
        ListenableFuture<Void> saveRecordDlld(
            @ThriftField(value=1, name="recordDlld", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RecordDlld recordDlld
        );

        @ThriftMethod(value = "updateRecordDlld")
        ListenableFuture<Void> updateRecordDlld(
            @ThriftField(value=1, name="recordDlld", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RecordDlld recordDlld
        );

        @ThriftMethod(value = "getWfdlList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TWfdlMessage>> getWfdlList(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfdlMessage record,
            @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "insMes")
        ListenableFuture<Void> insMes(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfdlMessage record
        );

        @ThriftMethod(value = "updMes")
        ListenableFuture<Void> updMes(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfdlMessage record
        );

        @ThriftMethod(value = "getWfdlCount")
        ListenableFuture<Long> getWfdlCount(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfdlMessage record
        );
    }
    void close();


    @ThriftMethod(value = "getMessById")
    com.igool.rpc.db.model.thrift.TWfdlMessage getMessById(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "delMes")
    void delMes(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "queryRecordDlld")
    com.igool.rpc.db.model.thrift.RecordDlld queryRecordDlld(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final long id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "saveRecordDlld")
    void saveRecordDlld(
        @ThriftField(value=1, name="recordDlld", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RecordDlld recordDlld
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updateRecordDlld")
    void updateRecordDlld(
        @ThriftField(value=1, name="recordDlld", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.RecordDlld recordDlld
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getWfdlList")
    List<com.igool.rpc.db.model.thrift.TWfdlMessage> getWfdlList(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfdlMessage record,
        @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "insMes")
    void insMes(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfdlMessage record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updMes")
    void updMes(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfdlMessage record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getWfdlCount")
    long getWfdlCount(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TWfdlMessage record
    ) throws org.apache.thrift.TException;
}