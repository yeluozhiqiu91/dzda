include "dzda/business.thrift"
include "dzda/vehicleflow.thrift"
namespace java com.igool.rpc.db.model.thrift
struct VehicleDetailDTO {
			1:string name,
			2:string nameHide,
			3:string plateNo,
			4:string vin,
			5:string vinHide,
			6:string engineNo,
			7:string engineNoHide,
			8:string plateType,
			9:string vehicleType,
			10:string brands,
			11:string model,
			12:string carColor,
			13:string useType,
			14:string carStatus,
			15:string registerDate,
			16:string period,
			17:string mobile,
			18:string mobileHide,
			19:list<business.Business> business,
			20:list<vehicleflow.VehicleFlow> vehicleflow,
			21:i32 syq
	}