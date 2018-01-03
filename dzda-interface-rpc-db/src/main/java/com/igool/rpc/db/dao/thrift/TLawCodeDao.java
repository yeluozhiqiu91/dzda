package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("TLawCodeDao")
public interface TLawCodeDao extends Closeable
{
    @ThriftService("TLawCodeDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getLawCount")
        ListenableFuture<Long> getLawCount(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TLawCode record
        );

        @ThriftMethod(value = "insLaw")
        ListenableFuture<Void> insLaw(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TLawCode record
        );

        @ThriftMethod(value = "getLawList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TLawCode>> getLawList(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TLawCode record,
            @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "delLaw")
        ListenableFuture<Void> delLaw(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "getLawCodeList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TLawCode>> getLawCodeList();

        @ThriftMethod(value = "getLaw")
        ListenableFuture<com.igool.rpc.db.model.thrift.TLawCode> getLaw(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
        );

        @ThriftMethod(value = "getLawByCode")
        ListenableFuture<com.igool.rpc.db.model.thrift.TLawCode> getLawByCode(
            @ThriftField(value=1, name="code", requiredness=Requiredness.NONE) final String code
        );

        @ThriftMethod(value = "getLawSelect")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TLawCode>> getLawSelect();

        @ThriftMethod(value = "updLaw")
        ListenableFuture<Void> updLaw(
            @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TLawCode record
        );
    }
    void close();


    @ThriftMethod(value = "getLawCount")
    long getLawCount(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TLawCode record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "insLaw")
    void insLaw(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TLawCode record
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getLawList")
    List<com.igool.rpc.db.model.thrift.TLawCode> getLawList(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TLawCode record,
        @ThriftField(value=2, name="pageStart", requiredness=Requiredness.NONE) final int pageStart,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "delLaw")
    void delLaw(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getLawCodeList")
    List<com.igool.rpc.db.model.thrift.TLawCode> getLawCodeList() throws org.apache.thrift.TException;

    @ThriftMethod(value = "getLaw")
    com.igool.rpc.db.model.thrift.TLawCode getLaw(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getLawByCode")
    com.igool.rpc.db.model.thrift.TLawCode getLawByCode(
        @ThriftField(value=1, name="code", requiredness=Requiredness.NONE) final String code
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getLawSelect")
    List<com.igool.rpc.db.model.thrift.TLawCode> getLawSelect() throws org.apache.thrift.TException;

    @ThriftMethod(value = "updLaw")
    void updLaw(
        @ThriftField(value=1, name="record", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.TLawCode record
    ) throws org.apache.thrift.TException;
}