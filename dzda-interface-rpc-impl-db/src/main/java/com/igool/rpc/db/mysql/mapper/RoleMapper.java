package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.Resource;
import com.igool.rpc.db.model.thrift.Roles;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by wang on 2017/9/8.
 */
public interface RoleMapper {
    List<Roles> getUserRoleList(@Param("uid") Integer uid);
    List<Resource> getRoleSourceList(@Param("roleId") Integer roleId);
    List<Roles> getRoleList(@Param("role") Roles roles, RowBounds rowBounds);
    long getRoleListCount(@Param("role") Roles roles, RowBounds rowBounds);

    void addRole(@Param("role") Roles role);

    Roles ajaxVerifyName(String name);

    void updateRole(@Param("role") Roles role);

    Roles findRoleByRid(@Param("roleId") int roleId);

    List<Integer> getRoleIdsByUserId(@Param("userId") int userId);

    void addUserRole(@Param("roleId") Integer roleId, @Param("userId") int userId, @Param("createUserId") int createUserId);

    void deleteUserRole(@Param("roleId") Integer roleId, @Param("userId") int userId, @Param("createUserId") int createUserId);
}
