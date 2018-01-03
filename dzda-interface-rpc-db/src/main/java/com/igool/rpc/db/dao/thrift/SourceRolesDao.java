package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("SourceRolesDao")
public interface SourceRolesDao extends Closeable
{
    @ThriftService("SourceRolesDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getAdminPermissionByUser")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Source>> getAdminPermissionByUser(
            @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user
        );
    }
    void close();


    @ThriftMethod(value = "getAdminPermissionByUser")
    List<com.igool.rpc.db.model.thrift.Source> getAdminPermissionByUser(
        @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user
    ) throws org.apache.thrift.TException;
}