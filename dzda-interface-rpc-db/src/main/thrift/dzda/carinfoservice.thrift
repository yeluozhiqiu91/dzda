include "dzda/vehicle.thrift"
include "dzda/findregister.thrift"

namespace java com.igool.rpc.db.service.thrift

	service CarInfoRegisterService {
        list<vehicle.Vehicle> findCarInfoBySfzAndName(1:string singleSfz, 2:string name, 3:string registertType),
        void addAccredit(1:findregister.FindRegister findRegister),
        list<findregister.FindRegister> findCarInfoByVehicles(1:list<vehicle.Vehicle> vehicles, 2:string singleSfz),
        list<findregister.FindRegister> findCarInfoByFindRegister(1:string accreditSfz, 2:string hphms,3:string carSfzs,4:string hpzls)
	}