package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.mysql.mapper.UserInfoMapper;
import com.igool.rpc.db.model.thrift.UserInfo;
import com.igool.rpc.db.service.thrift.UserInfoService;
import org.apache.ibatis.session.RowBounds;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wang on 2017/9/6.
 */
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public void close() {
        this.close();
    }

    @Override
    public List<UserInfo> getUserInfoList(@ThriftField(value = 1, name = "userinfo", requiredness = ThriftField.Requiredness.NONE) UserInfo userinfo, @ThriftField(value = 2, name = "pageIndex", requiredness = ThriftField.Requiredness.NONE) int pageIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        return userInfoMapper.getUserInfoList(userinfo,new RowBounds(pageIndex,pageSize));
    }

    @Override
    public long getUserInfoListCount(@ThriftField(value = 1, name = "userinfo", requiredness = ThriftField.Requiredness.NONE) UserInfo userinfo, @ThriftField(value = 2, name = "pageIndex", requiredness = ThriftField.Requiredness.NONE) int pageIndex, @ThriftField(value = 3, name = "pageSize", requiredness = ThriftField.Requiredness.NONE) int pageSize) throws TException {
        return userInfoMapper.getUserInfoListCount(userinfo,new RowBounds(pageIndex,pageSize));
    }

    @Override
    public void addUser(@ThriftField(value = 1, name = "userinfo", requiredness = ThriftField.Requiredness.NONE) UserInfo userinfo) throws TException {
        userInfoMapper.addUser(userinfo);
    }

    @Override
    public UserInfo findUserById(@ThriftField(value = 1, name = "uid", requiredness = ThriftField.Requiredness.NONE) String uid) throws TException {
        return userInfoMapper.findUserById(uid);
    }

    @Override
    public void updateUser(@ThriftField(value = 1, name = "userinfo", requiredness = ThriftField.Requiredness.NONE) UserInfo userinfo) throws TException {
        userInfoMapper.updateUser(userinfo);
    }

    @Override
    public UserInfo findUserByAccountAndPwd(@ThriftField(value = 1, name = "account", requiredness = ThriftField.Requiredness.NONE) String account, @ThriftField(value = 2, name = "password", requiredness = ThriftField.Requiredness.NONE) String password) throws TException {
        return userInfoMapper.findUserByAccountAndPwd(account,password);
    }

    @Override
    public void updatePassword(@ThriftField(value = 1, name = "userinfo", requiredness = ThriftField.Requiredness.NONE) UserInfo userinfo) throws TException {
        userInfoMapper.updatePassword(userinfo);
    }

    @Override
    public void resetPassword(@ThriftField(value = 1, name = "uid", requiredness = ThriftField.Requiredness.NONE) String uid, @ThriftField(value = 2, name = "password", requiredness = ThriftField.Requiredness.NONE) String password) throws TException {
        userInfoMapper.resetPassword(uid,password);
    }

    @Override
    public List<UserInfo> getAllUser() throws TException {
        return userInfoMapper.getAllUser();
    }

}
