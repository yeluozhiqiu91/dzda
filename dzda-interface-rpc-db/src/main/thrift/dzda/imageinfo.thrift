namespace java com.igool.rpc.db.model.thrift
struct ImageInfo {
			1:i32 imageId,
			2:string code,
			3:i32 status,
			4:string applicationDate,
			5:i32 businessTypeInfoId,
			6:i32 version,
			7:string node,
			8:i32 imageUser,
			9:string imageDate,
			10:string businessTypeInfoName,
			11:string userName,
			12:string fileCode
	}

struct BusinessImageCount {
            1:i32 xh,
            2:string manageDept,
            3:list<string> businessType,
            4:list<string> businessNum,
            5:i32 businessCount,
            6:i32 driverCount
    }

struct UserImageCount {
            1:i32 userId,
            2:string manageDept,
            3:string imageUser,
            4:string imageUserName,
            5:i32 businessNum,
            6:i32 driverNum
    }

struct MinAndMaxId {
            1:i32 minId,
            2:i32 maxId
    }