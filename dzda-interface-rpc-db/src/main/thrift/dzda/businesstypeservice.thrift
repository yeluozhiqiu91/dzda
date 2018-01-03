include "dzda/businesstypeinfo.thrift"
include "dzda/businesstypedetail.thrift"
namespace java com.igool.rpc.db.service.thrift

service BusinessTypeService {
		 	list<businesstypeinfo.BusinessTypeInfo> getAllBusinessType(),
		 	list<businesstypedetail.BusinessTypeDetail> findBusinessTypeDetailByYwlx(1:string ywlx),
		 	businesstypeinfo.BusinessTypeInfo findBusinessTypeByYwlx(1:string ywlx),
		 	list<businesstypedetail.BusinessTypeDetail> findBusinessTypeDetailAllBybusinessCode(1:string lsh),
		 	businesstypeinfo.BusinessTypeInfo findBusinessTypeByLsh(1:string lsh)
		 }