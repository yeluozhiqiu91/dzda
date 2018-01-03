package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.mysql.mapper.RoleResourceMapper;
import com.igool.rpc.db.model.thrift.Resource;
import com.igool.rpc.db.model.thrift.UserInfo;
import com.igool.rpc.db.service.thrift.RoleResourceService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/9/8.
 */
public class RoleResourceServiceImpl implements RoleResourceService{
    @Autowired
    RoleResourceMapper roleResourceMapper;
    @Override
    public void close() {
        this.close();
    }

    @Override
    public List<Resource> getAdminPermissionList(@ThriftField(value = 1, name = "userinfo", requiredness = ThriftField.Requiredness.NONE) UserInfo userinfo) throws TException {
        return roleResourceMapper.getAdminPermissionList(userinfo);
    }

    @Override
    public List<Resource> getResourceList() throws TException {
        return roleResourceMapper.getResourceList();
    }

    /**
     * 通过角色id得到资源id集合
     * @param roleId
     * @return
     * @throws TException
     */
    @Override
    public List<Integer> getResourceIdsByRoleId(@ThriftField(value = 1, name = "roleId", requiredness = ThriftField.Requiredness.NONE) int roleId) throws TException {
        return roleResourceMapper.getResourceIdsByRoleId(roleId);
    }

    @Override
    public void addResource(@ThriftField(value = 1, name = "resource", requiredness = ThriftField.Requiredness.NONE) Resource resource) throws TException {
        roleResourceMapper.addResource(resource);
    }

    @Override
    public Resource findResourceById(@ThriftField(value = 1, name = "id", requiredness = ThriftField.Requiredness.NONE) int id) throws TException {
        Resource resource=new Resource();
        resource=roleResourceMapper.findResourceById(id);
        return resource;
    }

    @Override
    public void updateResource(@ThriftField(value = 1, name = "resource", requiredness = ThriftField.Requiredness.NONE) Resource resource) throws TException {
        roleResourceMapper.updateResource(resource);
    }

    @Override
    public void saveRoleSource(@ThriftField(value = 1, name = "sourceIds", requiredness = ThriftField.Requiredness.NONE) String sourceIds, @ThriftField(value = 2, name = "roleId", requiredness = ThriftField.Requiredness.NONE) int roleId, @ThriftField(value = 3, name = "createUserId", requiredness = ThriftField.Requiredness.NONE) int createUserId) throws TException {
        List<Integer> oldResourceIds=roleResourceMapper.getResourceIdsByRoleId(roleId);
        List<Integer> newResourceIds=new ArrayList<>();
        if(sourceIds!=null&&!"".equals(sourceIds)){
            String arr[]=sourceIds.split(",");
            for(int i=0;i<arr.length;i++){
                newResourceIds.add(Integer.parseInt(arr[i]));
            }
        }
        List<Integer> intersection=new ArrayList<>(newResourceIds);
        intersection.retainAll(oldResourceIds);//获得交集
        newResourceIds.removeAll(intersection);//新增的权限
        oldResourceIds.removeAll(intersection);//删除的权限
        addRoleResource(newResourceIds,roleId,createUserId);
        deleteRoleResource(oldResourceIds,roleId,createUserId);
    }

    @Override
    public void addRoleResource(@ThriftField(value = 1, name = "sourceIds", requiredness = ThriftField.Requiredness.NONE) List<Integer> sourceIds, @ThriftField(value = 2, name = "roleId", requiredness = ThriftField.Requiredness.NONE) int roleId, @ThriftField(value = 3, name = "createUserId", requiredness = ThriftField.Requiredness.NONE) int createUserId) throws TException {
        if(sourceIds==null||sourceIds.size()==0){
            return;
        }
        for(int i=0;i<sourceIds.size();i++){
            roleResourceMapper.addRoleResource(sourceIds.get(i),roleId,createUserId);
        }
    }

    @Override
    public void deleteRoleResource(@ThriftField(value = 1, name = "sourceIds", requiredness = ThriftField.Requiredness.NONE) List<Integer> sourceIds, @ThriftField(value = 2, name = "roleId", requiredness = ThriftField.Requiredness.NONE) int roleId, @ThriftField(value = 3, name = "createUserId", requiredness = ThriftField.Requiredness.NONE) int createUserId) throws TException {
        if(sourceIds==null||sourceIds.size()==0){
            return;
        }
        for(int i=0;i<sourceIds.size();i++){
            roleResourceMapper.deleteRoleResource(sourceIds.get(i),roleId,createUserId);
        }
    }

}
