package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.Resource;
import com.igool.rpc.db.model.thrift.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wang on 2017/9/8.
 */
public interface RoleResourceMapper {
    List<Resource> getAdminPermissionList(@Param("userInfo") UserInfo userInfo);

    /**
     * 得到所有的资源
     * @return
     */
    List<Resource> getResourceList();

    /**
     * 增加资源
     * @param resource
     */
    void addResource(@Param("resource") Resource resource);

    /**
     * 通过id找到资源
     * @param id
     * @return
     */
    Resource findResourceById(@Param("id") int id);

    /**
     * 修改资源
     * @param resource
     */
    void updateResource(@Param("resource") Resource resource);

    /**
     * 添加角色资源
     * @param resourceId
     * @param roleId
     */
    void addRoleResource(@Param("resourceId") Integer resourceId,@Param("roleId") Integer roleId,@Param("createUserId") Integer createUserId);

    /**
     * 删除角色资源
     * @param resourceId
     * @param roleId
     */
    void deleteRoleResource(@Param("resourceId") Integer resourceId,@Param("roleId") Integer roleId,@Param("createUserId") Integer createUserId);

    /**
     * 通过角色id得到资源id集合
     * @param roleId
     * @return
     */
    List<Integer> getResourceIdsByRoleId(@Param("roleId") int roleId);
}
