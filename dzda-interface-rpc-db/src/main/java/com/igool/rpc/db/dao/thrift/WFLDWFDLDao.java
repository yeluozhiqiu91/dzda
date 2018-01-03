package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("WFLDWFDLDao")
public interface WFLDWFDLDao extends Closeable
{
    @ThriftService("WFLDWFDLDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "queryWfdlMessage")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.WFDLMessage>> queryWfdlMessage(
            @ThriftField(value=1, name="xzqh", requiredness=Requiredness.NONE) final String xzqh,
            @ThriftField(value=2, name="address", requiredness=Requiredness.NONE) final String address
        );

        @ThriftMethod(value = "queryWfldMessage")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.WFLDMessage>> queryWfldMessage(
            @ThriftField(value=1, name="xzqh", requiredness=Requiredness.NONE) final String xzqh,
            @ThriftField(value=2, name="dldm", requiredness=Requiredness.NONE) final String dldm
        );
    }
    void close();


    @ThriftMethod(value = "queryWfdlMessage")
    List<com.igool.rpc.db.model.thrift.WFDLMessage> queryWfdlMessage(
        @ThriftField(value=1, name="xzqh", requiredness=Requiredness.NONE) final String xzqh,
        @ThriftField(value=2, name="address", requiredness=Requiredness.NONE) final String address
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "queryWfldMessage")
    List<com.igool.rpc.db.model.thrift.WFLDMessage> queryWfldMessage(
        @ThriftField(value=1, name="xzqh", requiredness=Requiredness.NONE) final String xzqh,
        @ThriftField(value=2, name="dldm", requiredness=Requiredness.NONE) final String dldm
    ) throws org.apache.thrift.TException;
}