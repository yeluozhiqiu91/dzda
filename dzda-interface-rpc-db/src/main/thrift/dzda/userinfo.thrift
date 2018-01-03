include "dzda/resource.thrift"
namespace java com.igool.rpc.db.model.thrift
struct UserInfo {
			1:i32 userId,
            2:string account,
            3:string name,
            4:i32 departmentId,
            5:i32 isSuperAdmin,
            6:string password,
            7:string idCard,
            8:string updateDate,
            9:string lastDate,
            10:i32 status,
            11:i32 createUser,
            12:string createDate,
            13:i32 delUser,
            14:string delDate,
            15:i32 orderNum,
            16:list<resource.Resource> resourceList,
            17:string departmentName
	}