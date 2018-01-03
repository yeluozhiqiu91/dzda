namespace java com.igool.rpc.db.model.thrift
struct DeliveryInfo {
			1:i32 deliveryId,
			2:string code,
			3:i32 registerPerson,
			4:string registerDate,
			5:string createDate,
			6:i32 jjlx,
			7:string registerPersonName,
			8:string box,
			9:i32 signPerson,
			10:string signPersonName,
			11:i32 status,
			12:string statusStr,
			13:string receiverName,
            14:i32 count,
            15:i32 realJjlx,
            16:i32 signingId,
            17:i32 businessId
	}
