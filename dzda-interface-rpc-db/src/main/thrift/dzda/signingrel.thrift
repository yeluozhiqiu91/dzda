namespace java com.igool.rpc.db.model.thrift
struct SigningRel {
			1:i32 signingRelId,
			2:i32 signingId,
			3:i32 fileId,
			4:string createDate,
			5:i32 createUser,
			6:i32 status,
			7:i32 businessId
	}