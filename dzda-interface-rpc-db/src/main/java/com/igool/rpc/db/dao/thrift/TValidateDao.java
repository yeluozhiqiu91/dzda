package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("TValidateDao")
public interface TValidateDao extends Closeable
{
    @ThriftService("TValidateDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "insertTValidate")
        ListenableFuture<Void> insertTValidate(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TValidate record
        );

        @ThriftMethod(value = "getTValidateList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TValidate>> getTValidateList(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TValidate record,
            @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "delTValidate")
        ListenableFuture<Void> delTValidate(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "changeState")
        ListenableFuture<Void> changeState(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "getTValidateCount")
        ListenableFuture<Long> getTValidateCount(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TValidate record
        );

        @ThriftMethod(value = "getTValidateByCode")
        ListenableFuture<com.igool.rpc.db.model.thrift.TValidate> getTValidateByCode(
            @ThriftField(value=1, name="code", requiredness=Requiredness.NONE) final String code
        );
    }
    void close();


    @ThriftMethod(value = "insertTValidate")
    void insertTValidate(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TValidate record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTValidateList")
    List<com.igool.rpc.db.model.thrift.TValidate> getTValidateList(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TValidate record,
        @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "delTValidate")
    void delTValidate(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "changeState")
    void changeState(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTValidateCount")
    long getTValidateCount(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TValidate record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTValidateByCode")
    com.igool.rpc.db.model.thrift.TValidate getTValidateByCode(
        @ThriftField(value=1, name="code", requiredness=Requiredness.NONE) final String code
    ) throws org.apache.thrift.TException;
}