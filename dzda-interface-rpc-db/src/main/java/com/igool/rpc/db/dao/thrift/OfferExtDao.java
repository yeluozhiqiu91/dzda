package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("OfferExtDao")
public interface OfferExtDao extends Closeable
{
    @ThriftService("OfferExtDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getOfferExtByOfferId")
        ListenableFuture<com.igool.rpc.db.model.thrift.OfferExt> getOfferExtByOfferId(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
        );

        @ThriftMethod(value = "saveOrUpdateOfferExt")
        ListenableFuture<Void> saveOrUpdateOfferExt(
            @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.OfferExt arg0
        );
    }
    void close();


    @ThriftMethod(value = "getOfferExtByOfferId")
    com.igool.rpc.db.model.thrift.OfferExt getOfferExtByOfferId(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "saveOrUpdateOfferExt")
    void saveOrUpdateOfferExt(
        @ThriftField(value=1, name="arg0", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.OfferExt arg0
    ) throws org.apache.thrift.TException;
}