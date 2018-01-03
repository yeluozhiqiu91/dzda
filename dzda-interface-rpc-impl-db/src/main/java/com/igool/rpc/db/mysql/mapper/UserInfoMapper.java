package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by wang on 2017/9/6.
 */
public interface UserInfoMapper {
    List<UserInfo> getUserInfoList(@Param("userinfo") UserInfo userinfo, RowBounds rowBounds);

    void addUser(UserInfo userinfo);

    UserInfo findUserById(@Param("uid") String uid);

    void updateUser(@Param("userinfo") UserInfo userinfo);

    UserInfo findUserByAccountAndPwd(@Param("account") String account, @Param("password") String password);

    long getUserInfoListCount(@Param("userinfo") UserInfo userinfo, RowBounds rowBounds);

    void updatePassword(@Param("userinfo") UserInfo userInfo);

    void resetPassword(@Param("uid") String uid, @Param("password") String password);

    List<UserInfo> getAllUser();
}
