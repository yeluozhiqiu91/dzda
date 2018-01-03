include "dzda/businessinfo.thrift"
include "dzda/deliveryrel.thrift"
include "dzda/businesstypedetail.thrift"
include "dzda/fileinfo.thrift"

namespace java com.igool.rpc.db.service.thrift

	service FileInfoService {
		 	void addFileInfo(1:businessinfo.BusinessInfo businessinfo),
		 	map<fileinfo.FileInfo,list<businessinfo.BusinessInfo>> findFileInfoAndBusinessInfoByFileCode(1:string fileCode),
		 	void updateLshStatus(1:string type,2:string businessId,3:string fileId),
		 	list<businesstypedetail.BusinessTypeDetail> getBusinessTypeDetailByBusinessType(1:string businessId,2:string businessType),
		 	fileinfo.FileInfo findFileInfoByFileCode(1:string fileCode)
	}