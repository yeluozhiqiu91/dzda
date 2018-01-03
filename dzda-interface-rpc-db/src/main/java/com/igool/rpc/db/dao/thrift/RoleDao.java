package com.igool.rpc.db.dao.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("RoleDao")
public interface RoleDao extends Closeable
{
    @ThriftService("RoleDao")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "getListRole")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Roles>> getListRole(
            @ThriftField(value=1, name="role", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Roles role,
            @ThriftField(value=2, name="startIndex", requiredness=Requiredness.NONE) final int startIndex,
            @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
        );

        @ThriftMethod(value = "getTotalCountRole")
        ListenableFuture<Long> getTotalCountRole(
            @ThriftField(value=1, name="role", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Roles role
        );

        @ThriftMethod(value = "findRoleByRid")
        ListenableFuture<com.igool.rpc.db.model.thrift.Roles> findRoleByRid(
            @ThriftField(value=1, name="rid", requiredness=Requiredness.NONE) final String rid
        );

        @ThriftMethod(value = "addRole")
        ListenableFuture<Void> addRole(
            @ThriftField(value=1, name="role", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Roles role
        );

        @ThriftMethod(value = "updateRole")
        ListenableFuture<Void> updateRole(
            @ThriftField(value=1, name="role", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Roles role
        );

        @ThriftMethod(value = "delRoleByIds")
        ListenableFuture<Void> delRoleByIds(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );

        @ThriftMethod(value = "getRoleSourceList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Source>> getRoleSourceList(
            @ThriftField(value=1, name="rid", requiredness=Requiredness.NONE) final int rid
        );

        @ThriftMethod(value = "saveUserRole")
        ListenableFuture<Void> saveUserRole(
            @ThriftField(value=1, name="rids", requiredness=Requiredness.NONE) final String rids,
            @ThriftField(value=2, name="uid", requiredness=Requiredness.NONE) final int uid
        );

        @ThriftMethod(value = "saveRoleSource")
        ListenableFuture<Void> saveRoleSource(
            @ThriftField(value=1, name="sids", requiredness=Requiredness.NONE) final String sids,
            @ThriftField(value=2, name="rid", requiredness=Requiredness.NONE) final int rid
        );

        @ThriftMethod(value = "deleteRoleSource")
        ListenableFuture<Void> deleteRoleSource(
            @ThriftField(value=1, name="sidlist", requiredness=Requiredness.NONE) final List<Integer> sidlist,
            @ThriftField(value=2, name="rid", requiredness=Requiredness.NONE) final int rid
        );

        @ThriftMethod(value = "getUserRoleList")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Roles>> getUserRoleList(
            @ThriftField(value=1, name="uid", requiredness=Requiredness.NONE) final int uid
        );

        @ThriftMethod(value = "getAllListRole")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Roles>> getAllListRole();

        @ThriftMethod(value = "addUserRole")
        ListenableFuture<Void> addUserRole(
            @ThriftField(value=1, name="userRoles", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserRoles userRoles
        );

        @ThriftMethod(value = "getUserRoleCountByAid")
        ListenableFuture<Integer> getUserRoleCountByAid(
            @ThriftField(value=1, name="aid", requiredness=Requiredness.NONE) final int aid
        );

        @ThriftMethod(value = "updateUserRole")
        ListenableFuture<Void> updateUserRole(
            @ThriftField(value=1, name="userRoles", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserRoles userRoles
        );

        @ThriftMethod(value = "addRoleSource")
        ListenableFuture<Void> addRoleSource(
            @ThriftField(value=1, name="sidlist", requiredness=Requiredness.NONE) final List<Integer> sidlist,
            @ThriftField(value=2, name="rid", requiredness=Requiredness.NONE) final int rid
        );

        @ThriftMethod(value = "ajaxVerifyRole")
        ListenableFuture<com.igool.rpc.db.model.thrift.Roles> ajaxVerifyRole(
            @ThriftField(value=1, name="roleName", requiredness=Requiredness.NONE) final String roleName
        );

        @ThriftMethod(value = "deleteUserRole")
        ListenableFuture<Void> deleteUserRole(
            @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
        );
    }
    void close();


    @ThriftMethod(value = "getListRole")
    List<com.igool.rpc.db.model.thrift.Roles> getListRole(
        @ThriftField(value=1, name="role", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Roles role,
        @ThriftField(value=2, name="startIndex", requiredness=Requiredness.NONE) final int startIndex,
        @ThriftField(value=3, name="pageSize", requiredness=Requiredness.NONE) final int pageSize
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getTotalCountRole")
    long getTotalCountRole(
        @ThriftField(value=1, name="role", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Roles role
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "findRoleByRid")
    com.igool.rpc.db.model.thrift.Roles findRoleByRid(
        @ThriftField(value=1, name="rid", requiredness=Requiredness.NONE) final String rid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "addRole")
    void addRole(
        @ThriftField(value=1, name="role", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Roles role
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updateRole")
    void updateRole(
        @ThriftField(value=1, name="role", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.Roles role
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "delRoleByIds")
    void delRoleByIds(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getRoleSourceList")
    List<com.igool.rpc.db.model.thrift.Source> getRoleSourceList(
        @ThriftField(value=1, name="rid", requiredness=Requiredness.NONE) final int rid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "saveUserRole")
    void saveUserRole(
        @ThriftField(value=1, name="rids", requiredness=Requiredness.NONE) final String rids,
        @ThriftField(value=2, name="uid", requiredness=Requiredness.NONE) final int uid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "saveRoleSource")
    void saveRoleSource(
        @ThriftField(value=1, name="sids", requiredness=Requiredness.NONE) final String sids,
        @ThriftField(value=2, name="rid", requiredness=Requiredness.NONE) final int rid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "deleteRoleSource")
    void deleteRoleSource(
        @ThriftField(value=1, name="sidlist", requiredness=Requiredness.NONE) final List<Integer> sidlist,
        @ThriftField(value=2, name="rid", requiredness=Requiredness.NONE) final int rid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getUserRoleList")
    List<com.igool.rpc.db.model.thrift.Roles> getUserRoleList(
        @ThriftField(value=1, name="uid", requiredness=Requiredness.NONE) final int uid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getAllListRole")
    List<com.igool.rpc.db.model.thrift.Roles> getAllListRole() throws org.apache.thrift.TException;

    @ThriftMethod(value = "addUserRole")
    void addUserRole(
        @ThriftField(value=1, name="userRoles", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserRoles userRoles
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getUserRoleCountByAid")
    int getUserRoleCountByAid(
        @ThriftField(value=1, name="aid", requiredness=Requiredness.NONE) final int aid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "updateUserRole")
    void updateUserRole(
        @ThriftField(value=1, name="userRoles", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.UserRoles userRoles
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "addRoleSource")
    void addRoleSource(
        @ThriftField(value=1, name="sidlist", requiredness=Requiredness.NONE) final List<Integer> sidlist,
        @ThriftField(value=2, name="rid", requiredness=Requiredness.NONE) final int rid
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "ajaxVerifyRole")
    com.igool.rpc.db.model.thrift.Roles ajaxVerifyRole(
        @ThriftField(value=1, name="roleName", requiredness=Requiredness.NONE) final String roleName
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "deleteUserRole")
    void deleteUserRole(
        @ThriftField(value=1, name="id", requiredness=Requiredness.NONE) final String id
    ) throws org.apache.thrift.TException;
}