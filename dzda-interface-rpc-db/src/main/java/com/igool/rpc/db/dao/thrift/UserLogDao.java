package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("UserLogDao")
public interface UserLogDao extends Closeable
{
    @ThriftService("UserLogDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getNowUserLogin")
        ListenableFuture<Integer> getNowUserLogin(
            @ThriftField(value=1, name="date", requiredness=Requiredness.NONE) final long date
        );

        @ThriftMethod(value = "addUserLog")
        ListenableFuture<Void> addUserLog(
            @ThriftField(value=1, name="userlog", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserLog userlog
        );

        @ThriftMethod(value = "getUserLog")
        ListenableFuture<Integer> getUserLog(
            @ThriftField(value=1, name="userLogLogin", requiredness=Requiredness.NONE) final int userLogLogin
        );
    }
    void close();


    @ThriftMethod(value = "getNowUserLogin")
    int getNowUserLogin(
        @ThriftField(value=1, name="date", requiredness=Requiredness.NONE) final long date
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "addUserLog")
    void addUserLog(
        @ThriftField(value=1, name="userlog", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserLog userlog
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getUserLog")
    int getUserLog(
        @ThriftField(value=1, name="userLogLogin", requiredness=Requiredness.NONE) final int userLogLogin
    ) throws org.apache.thrift.TException;
}