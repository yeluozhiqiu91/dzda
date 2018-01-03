include "dzda/imagedetail.thrift"
namespace java com.igool.rpc.db.model.thrift
struct ImageDetailDTO {
			1:string plateNo,
			2:string number,
			3:string name,
			4:string time,
			5:list<imagedetail.ImageDetail> pic
	}