namespace java com.igool.rpc.db.model.thrift
struct Resource {
			1:i32 moduleId,
			2:string name,
			3:string path,
			4:i32 parentId,
			5:string code,
			6:i32 orderNumber,
			7:string remarks,
			8:i32 status,
			9:i32 createUser,
			10:string createDate,
			11:i32 updateUser,
			12:string updateDate,
			13:i32 isSelected
	}