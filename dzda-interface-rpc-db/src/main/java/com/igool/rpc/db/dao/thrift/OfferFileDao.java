package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("OfferFileDao")
public interface OfferFileDao extends Closeable
{
    @ThriftService("OfferFileDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getOfferVideo")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.OfferVideo>> getOfferVideo(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
        );

        @ThriftMethod(value = "saveImage")
        ListenableFuture<Void> saveImage(
            @ThriftField(value=1, name="offerImage", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.OfferImage offerImage
        );

        @ThriftMethod(value = "saveScreenShotTime")
        ListenableFuture<Void> saveScreenShotTime(
            @ThriftField(value=1, name="screenShotTime", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.ScreenShotTime screenShotTime
        );

        @ThriftMethod(value = "getOfferImage")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.OfferImage>> getOfferImage(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id,
            @ThriftField(value=2, name="type", requiredness=Requiredness.NONE) final int type
        );
    }
    void close();


    @ThriftMethod(value = "getOfferVideo")
    List<com.igool.rpc.db.model.thrift.OfferVideo> getOfferVideo(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "saveImage")
    void saveImage(
        @ThriftField(value=1, name="offerImage", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.OfferImage offerImage
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "saveScreenShotTime")
    void saveScreenShotTime(
        @ThriftField(value=1, name="screenShotTime", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.ScreenShotTime screenShotTime
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getOfferImage")
    List<com.igool.rpc.db.model.thrift.OfferImage> getOfferImage(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id,
        @ThriftField(value=2, name="type", requiredness=Requiredness.NONE) final int type
    ) throws org.apache.thrift.TException;
}