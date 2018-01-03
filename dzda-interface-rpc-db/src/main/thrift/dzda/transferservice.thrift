include "dzda/businessinfo.thrift"
include "dzda/deliveryinfo.thrift"
include "dzda/fileid.thrift"
include "dzda/deliveryrel.thrift"
namespace java com.igool.rpc.db.service.thrift

service TransferService {
    businessinfo.BusinessInfo findNativeBusinessInfo(1:string lsh),
    deliveryinfo.DeliveryInfo findDeliveryInfoByPcCode(1:string pcCode),
    list<deliveryinfo.DeliveryInfo> getDeliveryListBza(1:string pcCode,2:string startTime,3:string endTime,4:i32 startIndex,5:i32 pageSize),
    i64 getDeliveryListCountBza(1:string pcCode,2:string startTime,3:string endTime,4:i32 startIndex,5:i32 pageSize),
    list<businessinfo.BusinessInfo> findBusinessInfoByPcCodeAndLszt(1:string pcCode,2:i32 lszt),
    void BToASign(1:businessinfo.BusinessInfo businessInfo,2:string pcCode,3:i32 userId),
    list<fileid.FileId> findAllFileIdsByCkStatus(1:i32 ckStatus),
    list<fileid.FileId> findAllForFileCode(),
    list<fileid.FileId> findAllForBox(),
    i32 findAllForFileCodeCount(),
    i32 findAllForBoxCount(),
    list<fileid.FileId> findForFileCode(1:i32 startIndex,2:i32 pageSize)
}