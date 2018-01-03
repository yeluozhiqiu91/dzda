package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.mysql.mapper.RoleMapper;
import com.igool.rpc.db.model.thrift.Resource;
import com.igool.rpc.db.model.thrift.Roles;
import com.igool.rpc.db.service.thrift.RoleService;
import org.apache.ibatis.session.RowBounds;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/9/8.
 */
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleMapper roleMapper;
    @Override
    public void close() {
        this.close();
    }

    @Override
    public List<Roles> getUserRoleList(@ThriftField(value = 1, name = "uid", requiredness = ThriftField.Requiredness.NONE) int uid) throws TException {
        return roleMapper.getUserRoleList(uid);
    }

    @Override
    public List<Resource> getRoleSourceList(@ThriftField(value = 1, name = "roleId", requiredness = ThriftField.Requiredness.NONE) int roleId) throws TException {
        return roleMapper.getRoleSourceList(roleId);
    }

    @Override
    public List<Roles> getRoleList(@ThriftField(value = 1, name = "role", requiredness = ThriftField.Requiredness.NONE) Roles role, @ThriftField(value = 2, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        return roleMapper.getRoleList(role,new RowBounds(startIndex,pageSize));
    }

    @Override
    public long getRoleListCount(@ThriftField(value = 1, name = "role", requiredness = ThriftField.Requiredness.NONE) Roles role, @ThriftField(value = 2, name = "startIndex", requiredness = ThriftField.Requiredness.NONE) int startIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        return roleMapper.getRoleListCount(role,new RowBounds(startIndex,pageSize));
    }

    @Override
    public void addRole(@ThriftField(value = 1, name = "role", requiredness = ThriftField.Requiredness.NONE) Roles role) throws TException {
        roleMapper.addRole(role);
    }

    @Override
    public Roles ajaxVerifyName(@ThriftField(value = 1, name = "name", requiredness = ThriftField.Requiredness.NONE) String name) throws TException {
        Roles r=roleMapper.ajaxVerifyName(name);
        if(r!=null){
            return r;
        }else{
            return new Roles();
        }
    }

    @Override
    public void updateRole(@ThriftField(value = 1, name = "role", requiredness = ThriftField.Requiredness.NONE) Roles role) throws TException {
        roleMapper.updateRole(role);
    }

    @Override
    public Roles findRoleByRid(@ThriftField(value = 1, name = "roleId", requiredness = ThriftField.Requiredness.NONE) int roleId) throws TException {
        return roleMapper.findRoleByRid(roleId);
    }

    @Override
    public List<Integer> getRoleIdsByUserId(@ThriftField(value = 1, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId) throws TException {
        return roleMapper.getRoleIdsByUserId(userId);
    }

    @Override
    public void saveUserRole(@ThriftField(value = 1, name = "roleIds", requiredness = ThriftField.Requiredness.NONE) String roleIds, @ThriftField(value = 2, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId, @ThriftField(value = 3, name = "createUserId", requiredness = ThriftField.Requiredness.NONE) int createUserId) throws TException {
        List<Integer> oldRoleIds=roleMapper.getRoleIdsByUserId(userId);
        List<Integer> newRoleIds=new ArrayList<>();
        if(roleIds!=null&&!"".equals(roleIds)){
            String arr[]=roleIds.split(",");
            for(int i=0;i<arr.length;i++){
                newRoleIds.add(Integer.parseInt(arr[i]));
            }
        }
        List<Integer> intersection=new ArrayList<>(newRoleIds);
        intersection.retainAll(oldRoleIds);//获得交集
        newRoleIds.removeAll(intersection);//新增的角色
        oldRoleIds.removeAll(intersection);//删除的角色
        addUserRole(newRoleIds,userId,createUserId);
        deleteUserRole(oldRoleIds,userId,createUserId);
    }

    @Override
    public void addUserRole(@ThriftField(value = 1, name = "roleIds", requiredness = ThriftField.Requiredness.NONE) List<Integer> roleIds, @ThriftField(value = 2, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId, @ThriftField(value = 3, name = "createUserId", requiredness = ThriftField.Requiredness.NONE) int createUserId) throws TException {
        if(roleIds==null||roleIds.size()==0){
            return;
        }
        for(int i=0;i<roleIds.size();i++){
            roleMapper.addUserRole(roleIds.get(i),userId,createUserId);
        }
    }

    @Override
    public void deleteUserRole(@ThriftField(value = 1, name = "roleIds", requiredness = ThriftField.Requiredness.NONE) List<Integer> roleIds, @ThriftField(value = 2, name = "userId", requiredness = ThriftField.Requiredness.NONE) int userId, @ThriftField(value = 3, name = "createUserId", requiredness = ThriftField.Requiredness.NONE) int createUserId) throws TException {
        if(roleIds==null||roleIds.size()==0){
            return;
        }
        for(int i=0;i<roleIds.size();i++){
            roleMapper.deleteUserRole(roleIds.get(i),userId,createUserId);
        }
    }
}
