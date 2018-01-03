// Generated by swift-generator from dzda/platetypeservice.thrift

package com.igool.rpc.db.service.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;


@ThriftService("PlateTypeService")
public interface PlateTypeService extends Closeable
{
    @ThriftService("PlateTypeService")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getAllPlateType")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.PlateType>> getAllPlateType();
    }
    void close();


    @ThriftMethod(value = "getAllPlateType")
    List<com.igool.rpc.db.model.thrift.PlateType> getAllPlateType() throws org.apache.thrift.TException;
}