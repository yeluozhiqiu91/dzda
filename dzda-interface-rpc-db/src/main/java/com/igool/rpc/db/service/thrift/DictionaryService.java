// Generated by swift-generator from dzda/dictionaryservice.thrift

package com.igool.rpc.db.service.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;


@ThriftService("DictionaryService")
public interface DictionaryService extends Closeable
{
    @ThriftService("DictionaryService")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "findAllDictionary")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Dictionary>> findAllDictionary();
    }
    void close();


    @ThriftMethod(value = "findAllDictionary")
    List<com.igool.rpc.db.model.thrift.Dictionary> findAllDictionary() throws org.apache.thrift.TException;
}