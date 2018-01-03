include "dzda/userinfo.thrift"
include "dzda/resource.thrift"
namespace java com.igool.rpc.db.service.thrift

service UserInfoService {
		 	list<userinfo.UserInfo> getUserInfoList(1:userinfo.UserInfo userinfo,2:i32 pageIndex,3:i32 pageSize),
		 	i64 getUserInfoListCount(1:userinfo.UserInfo userinfo,2:i32 pageIndex,3:i32 pageSize),
		 	void addUser(1:userinfo.UserInfo userinfo),
		 	userinfo.UserInfo findUserById(1:string uid),
		 	void updateUser(1:userinfo.UserInfo userinfo),
		 	userinfo.UserInfo findUserByAccountAndPwd(1:string account,2:string password),
		 	void updatePassword(1:userinfo.UserInfo userinfo),
		 	void resetPassword(1:string uid,2:string password),
		 	list<userinfo.UserInfo> getAllUser()
	}
service RoleResourceService{
    list<resource.Resource> getAdminPermissionList(1:userinfo.UserInfo userinfo),
    list<resource.Resource> getResourceList(),
    list<i32> getResourceIdsByRoleId(1:i32 roleId),
    void addResource(1:resource.Resource resource),
    resource.Resource findResourceById(1:i32 id),
    void updateResource(1:resource.Resource resource),
    void saveRoleSource(1:string sourceIds,2:i32 roleId,3:i32 createUserId),
    void addRoleResource(1:list<i32> sourceIds,2:i32 roleId,3:i32 createUserId),
    void deleteRoleResource(1:list<i32> sourceIds,2:i32 roleId,3:i32 createUserId)
}