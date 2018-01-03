// Generated by swift-generator from dzda/userinfoservice.thrift

package com.igool.rpc.db.service.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;


@ThriftService("UserInfoService")
public interface UserInfoService extends Closeable
{
    @ThriftService("UserInfoService")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getUserInfoList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.UserInfo>> getUserInfoList(
            @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo,
            @ThriftField(value=2, name="pageIndex", requiredness=Requiredness.NONE) final int pageIndex,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "getUserInfoListCount")
        ListenableFuture<Long> getUserInfoListCount(
            @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo,
            @ThriftField(value=2, name="pageIndex", requiredness=Requiredness.NONE) final int pageIndex,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "addUser")
        ListenableFuture<Void> addUser(
            @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo
        );

        @ThriftMethod(value = "findUserById")
        ListenableFuture<com.igool.rpc.db.model.thrift.UserInfo> findUserById(
            @ThriftField(value=1, name="uid", requiredness=Requiredness.NONE) final String uid
        );

        @ThriftMethod(value = "updateUser")
        ListenableFuture<Void> updateUser(
            @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo
        );

        @ThriftMethod(value = "findUserByAccountAndPwd")
        ListenableFuture<com.igool.rpc.db.model.thrift.UserInfo> findUserByAccountAndPwd(
            @ThriftField(value=1, name="account", requiredness=Requiredness.NONE) final String account,
            @ThriftField(value=2, name="password", requiredness=Requiredness.NONE) final String password
        );

        @ThriftMethod(value = "updatePassword")
        ListenableFuture<Void> updatePassword(
            @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo
        );

        @ThriftMethod(value = "resetPassword")
        ListenableFuture<Void> resetPassword(
            @ThriftField(value=1, name="uid", requiredness=Requiredness.NONE) final String uid,
            @ThriftField(value=2, name="password", requiredness=Requiredness.NONE) final String password
        );

        @ThriftMethod(value = "getAllUser")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.UserInfo>> getAllUser();
    }
    void close();


    @ThriftMethod(value = "getUserInfoList")
    List<com.igool.rpc.db.model.thrift.UserInfo> getUserInfoList(
        @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo,
        @ThriftField(value=2, name="pageIndex", requiredness=Requiredness.NONE) final int pageIndex,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getUserInfoListCount")
    long getUserInfoListCount(
        @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo,
        @ThriftField(value=2, name="pageIndex", requiredness=Requiredness.NONE) final int pageIndex,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "addUser")
    void addUser(
        @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "findUserById")
    com.igool.rpc.db.model.thrift.UserInfo findUserById(
        @ThriftField(value=1, name="uid", requiredness=Requiredness.NONE) final String uid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updateUser")
    void updateUser(
        @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "findUserByAccountAndPwd")
    com.igool.rpc.db.model.thrift.UserInfo findUserByAccountAndPwd(
        @ThriftField(value=1, name="account", requiredness=Requiredness.NONE) final String account,
        @ThriftField(value=2, name="password", requiredness=Requiredness.NONE) final String password
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updatePassword")
    void updatePassword(
        @ThriftField(value=1, name="userinfo", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserInfo userinfo
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "resetPassword")
    void resetPassword(
        @ThriftField(value=1, name="uid", requiredness=Requiredness.NONE) final String uid,
        @ThriftField(value=2, name="password", requiredness=Requiredness.NONE) final String password
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getAllUser")
    List<com.igool.rpc.db.model.thrift.UserInfo> getAllUser() throws org.apache.thrift.TException;
}