include "dzda/vehicleoperator.thrift"
include "dzda/findregister.thrift"
include "dzda/vehicledetaildto.thrift"
include "dzda/imagedetail.thrift"
include "dzda/imageinfo.thrift"
include "dzda/business.thrift"
namespace java com.igool.rpc.db.service.thrift

service VehicleService {
            void addVehicleOperator(1:vehicleoperator.VehicleOperator vehicleoperator),
            list<findregister.FindRegister> getFindRegisterByTypeAndStr(1:string str),
            list<vehicledetaildto.VehicleDetailDTO> findAllVehicleBySfz(1:string sfzs,2:string hphms,3:string hpzls),
            list<imagedetail.ImageDetail> findAllImageByLsh(1:string lsh),
            list<imageinfo.ImageInfo> findAllImageInfoByBusinesses(1:list<business.Business> businessList)
	}