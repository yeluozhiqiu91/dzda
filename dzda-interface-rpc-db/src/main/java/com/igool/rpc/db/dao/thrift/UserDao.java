package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("UserDao")
public interface UserDao extends Closeable
{
    @ThriftService("UserDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "ajaxVerifyName")
        ListenableFuture<com.igool.rpc.db.model.thrift.User> ajaxVerifyName(
            @ThriftField(value=1, name="idcard", requiredness=Requiredness.NONE) final String idcard
        );

        @ThriftMethod(value = "findUserNameAndPwd")
        ListenableFuture<com.igool.rpc.db.model.thrift.User> findUserNameAndPwd(
            @ThriftField(value=1, name="idcard", requiredness=Requiredness.NONE) final String idcard,
            @ThriftField(value=2, name="pwd", requiredness=Requiredness.NONE) final String pwd
        );

        @ThriftMethod(value = "findUserByUid")
        ListenableFuture<com.igool.rpc.db.model.thrift.User> findUserByUid(
            @ThriftField(value=1, name="uid", requiredness=Requiredness.NONE) final String uid
        );

        @ThriftMethod(value = "updatePwd")
        ListenableFuture<Void> updatePwd(
            @ThriftField(value=1, name="pwd", requiredness=Requiredness.NONE) final String pwd,
            @ThriftField(value=2, name="id", requiredness=Requiredness.NONE) final int id
        );

        @ThriftMethod(value = "addUser")
        ListenableFuture<Void> addUser(
            @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user
        );

        @ThriftMethod(value = "updateUser")
        ListenableFuture<Void> updateUser(
            @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user
        );

        @ThriftMethod(value = "getList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.User>> getList(
            @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user,
            @ThriftField(value=2, name="startIndex", requiredness=Requiredness.NONE) final int startIndex,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "getListCount")
        ListenableFuture<Long> getListCount(
            @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user
        );

        @ThriftMethod(value = "getTRegionList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.TRegion>> getTRegionList();

        @ThriftMethod(value = "getTRegionById")
        ListenableFuture<com.igool.rpc.db.model.thrift.TRegion> getTRegionById(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
        );

        @ThriftMethod(value = "delUserByIds")
        ListenableFuture<Void> delUserByIds(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "getUserById")
        ListenableFuture<com.igool.rpc.db.model.thrift.SSPUser> getUserById(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
        );

        @ThriftMethod(value = "getUserId")
        ListenableFuture<Integer> getUserId();
    }
    void close();


    @ThriftMethod(value = "ajaxVerifyName")
    com.igool.rpc.db.model.thrift.User ajaxVerifyName(
        @ThriftField(value=1, name="idcard", requiredness=Requiredness.NONE) final String idcard
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "findUserNameAndPwd")
    com.igool.rpc.db.model.thrift.User findUserNameAndPwd(
        @ThriftField(value=1, name="idcard", requiredness=Requiredness.NONE) final String idcard,
        @ThriftField(value=2, name="pwd", requiredness=Requiredness.NONE) final String pwd
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "findUserByUid")
    com.igool.rpc.db.model.thrift.User findUserByUid(
        @ThriftField(value=1, name="uid", requiredness=Requiredness.NONE) final String uid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updatePwd")
    void updatePwd(
        @ThriftField(value=1, name="pwd", requiredness=Requiredness.NONE) final String pwd,
        @ThriftField(value=2, name="id", requiredness=Requiredness.NONE) final int id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "addUser")
    void addUser(
        @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updateUser")
    void updateUser(
        @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getList")
    List<com.igool.rpc.db.model.thrift.User> getList(
        @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user,
        @ThriftField(value=2, name="startIndex", requiredness=Requiredness.NONE) final int startIndex,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getListCount")
    long getListCount(
        @ThriftField(value=1, name="user", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.User user
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTRegionList")
    List<com.igool.rpc.db.model.thrift.TRegion> getTRegionList() throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTRegionById")
    com.igool.rpc.db.model.thrift.TRegion getTRegionById(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "delUserByIds")
    void delUserByIds(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getUserById")
    com.igool.rpc.db.model.thrift.SSPUser getUserById(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final int id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getUserId")
    int getUserId() throws org.apache.thrift.TException;
}