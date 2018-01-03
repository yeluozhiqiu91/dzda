include "dzda/vehicleflow.thrift"
namespace java com.igool.rpc.db.model.thrift
struct VehicleDetail {
        1:string syr,
        2:string hphm,
        3:string clsbdh,
        4:string fdjh,
        5:string hpzl,
        6:string cllx,
        7:string clxh,
        8:string csys,
        9:string syxz,
        10:string zt,
        11:string ccdjrq,
        12:string yxqz,
        13:string sjhm,
        14:string dabh,
        15:list<vehicleflow.VehicleFlow> vehicleFlows,
        16:string sfz
	}