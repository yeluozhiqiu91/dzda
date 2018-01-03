namespace java com.igool.rpc.db.model.thrift

struct Department{
			1:i32 departmentId,
			2:string name,
			3:string code,
			4:i32 parentId,
			5:i32 sort,
			6:string remarks,
			7:i32 createUser,
			8:string createDate,
			9:i32 status,
			10:i32 delUser,
			11:string delDate
	}
