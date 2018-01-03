include "dzda/roles.thrift"
include "dzda/resource.thrift"
namespace java com.igool.rpc.db.service.thrift

service RoleService {
		 	list<roles.Roles> getUserRoleList(1:i32 uid),
		 	list<resource.Resource> getRoleSourceList(1:i32 roleId),
		 	list<roles.Roles> getRoleList(1:roles.Roles role, 2:i32 startIndex, 3:i32 pageSize),
		 	i64 getRoleListCount(1:roles.Roles role, 2:i32 startIndex, 3:i32 pageSize),
		 	void addRole(1:roles.Roles role),
		 	roles.Roles ajaxVerifyName(1:string name),
		 	void updateRole(1:roles.Roles role),
		 	roles.Roles findRoleByRid(1:i32 roleId),
		 	list<i32> getRoleIdsByUserId(1:i32 userId),
		 	void saveUserRole(1:string roleIds,2:i32 userId,3:i32 createUserId),
            void addUserRole(1:list<i32> roleIds,2:i32 userId,3:i32 createUserId),
            void deleteUserRole(1:list<i32> roleIds,2:i32 userId,3:i32 createUserId)
		 }