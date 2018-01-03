package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("SourceDao")
public interface SourceDao extends Closeable
{
    @ThriftService("SourceDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getSourceList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Source>> getSourceList();

        @ThriftMethod(value = "addSource")
        ListenableFuture<Void> addSource(
            @ThriftField(value=1, name="source", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Source source
        );

        @ThriftMethod(value = "findSourceById")
        ListenableFuture<com.igool.rpc.db.model.thrift.Source> findSourceById(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "updateSource")
        ListenableFuture<Void> updateSource(
            @ThriftField(value=1, name="source", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Source source
        );
    }
    void close();


    @ThriftMethod(value = "getSourceList")
    List<com.igool.rpc.db.model.thrift.Source> getSourceList() throws org.apache.thrift.TException;

    @ThriftMethod(value = "addSource")
    void addSource(
        @ThriftField(value=1, name="source", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Source source
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "findSourceById")
    com.igool.rpc.db.model.thrift.Source findSourceById(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updateSource")
    void updateSource(
        @ThriftField(value=1, name="source", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Source source
    ) throws org.apache.thrift.TException;
}