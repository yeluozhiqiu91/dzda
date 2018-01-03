include "dzda/businessinfo.thrift"
include "dzda/wtda.thrift"
include "dzda/jjtj.thrift"
include "dzda/lsmx.thrift"
include "dzda/deliveryinfo.thrift"
include "dzda/userinfo.thrift"
include "dzda/businessimagedto.thrift"
namespace java com.igool.rpc.db.service.thrift

	service BusinessInfoService {
		 	businessinfo.BusinessInfo findBusinessInfoByCode(1:string code),
		 	void deleteBusinessInfo(1:businessinfo.BusinessInfo businessInfo),
		 	list<businessinfo.BusinessInfo> getBusinessInfoList(1:businessinfo.BusinessInfo businessInfo,2:i32 startIndex, 3:i32 pageSize),
		 	i64 getBusinessInfoListCount(1:businessinfo.BusinessInfo businessInfo,2:i32 startIndex, 3:i32 pageSize),
		 	list<businessinfo.BusinessInfo> getBusinessInfoListForShow(1:businessinfo.BusinessInfo businessInfo,2:i32 startIndex, 3:i32 pageSize),
            i64 getBusinessInfoListForShowCount(1:businessinfo.BusinessInfo businessInfo,2:i32 startIndex, 3:i32 pageSize),
            list<wtda.Wtda> getWtdaList(1:string startTime,2:string endTime,3:i32 startIndex,4:i32 pageSize),
            i64 getWtdaListCount(1:string startTime,2:string endTime,3:i32 startIndex,4:i32 pageSize),
            list<jjtj.Jjtj> getDeliveryList(1:string startTime,2:string endTime),
            list<jjtj.Jjtj> getDeliveryAndSignList(1:string startTime,2:string endTime,3:i32 startIndex,4:i32 pageSize),
            i64 getDeliveryAndSignListCount(1:string startTime,2:string endTime,3:i32 startIndex,4:i32 pageSize),
            list<jjtj.Jjtj> getSignList(1:string startTime,2:string endTime),
            list<jjtj.Jjtj> getOldDeliveryList(1:string startTime,2:string endTime),
            list<jjtj.Jjtj> getOldSignList(1:string startTime,2:string endTime),
            list<lsmx.Lsmx> getOldLsmxList(1:string startTime,2:string endTime,3:i32 jjlx),
            list<lsmx.Lsmx> getOldDamxList(1:string startTime,2:string endTime,3:i32 jjlx),
            list<lsmx.Lsmx> getNewLsmxList(1:string startTime,2:string endTime,3:i32 jjlx,4:i32 startIndex,5:i32 pageSize),
            list<lsmx.Lsmx> getNewDamxList(1:string startTime,2:string endTime,3:i32 jjlx,4:i32 startIndex,5:i32 pageSize),
            deliveryinfo.DeliveryInfo addAToBRegister(1:list<businessinfo.BusinessInfo> businessInfos,2:string box,3:i32 userId),
            businessinfo.BusinessInfo findNativeBusinessInfoByLsh(1:string lsh),
            string BToARegister(1:list<businessinfo.BusinessInfo> businessInfos,2:i32 userId),
            list<businessinfo.BusinessInfo> findNativeBusinessInfoByDeliveryPcCode(1:string pcCode),
            i32 saveBusinessInfo(1:businessinfo.BusinessInfo businessInfo),
            list<deliveryinfo.DeliveryInfo> findDeliveryByCodeAndBoxAndDate(1:string code,2:string box,3:string start,4:string end,5:i32 indexPage,6:i32 pageCount),
            i64 findDeliveryByCodeAndBoxAndDateCount(1:string code,2:string box,3:string start,4:string end,5:i32 indexPage,6:i32 pageCount),
            list<deliveryinfo.DeliveryInfo> getJjlxByCodes(1:list<deliveryinfo.DeliveryInfo> deliveryinfos),
            list<businessinfo.BusinessInfo> ajaxLookSignDetailByCode(1:string code,2:string bs),
            businessinfo.BusinessInfo findBoxByDeliveryIdAndLsh(1:string code,2:string deliveryId),
            i32 signBoxInAToB(1:businessinfo.BusinessInfo businessInfo,2:i32 userId,3:string deliveryId),
            list<deliveryinfo.DeliveryInfo> getBusinessInfoListByDateOrCodeRefUser(1:string code,2:userinfo.UserInfo user,3:string startTime,4:string endTime,5:i32 indexPage,6:i32 pageCount,7:list<userinfo.UserInfo> users),
            i32 getBusinessInfoListByDateOrCodeRefUserCount(1:string code,2:userinfo.UserInfo user,3:string startTime,4:string endTime,7:list<userinfo.UserInfo> users),
            i32 updateBox(1:string box,2:string beforeBox),
            list<businessinfo.BusinessInfo> getBusinessInfoListByBox(1:string box,2:string deliveryId,3:string deliveryCode),
            void delDeliveryRel(1:string ids,2:string box,3:string deliveryId,4:i32 userId,5:string deliveryCode),
            list<businessinfo.BusinessInfo> getBusinessInfoListForImage(1:businessinfo.BusinessInfo businessInfo,2:i32 startIndex, 3:i32 pageSize),
            i64 getBusinessInfoListForImageCount(1:businessinfo.BusinessInfo businessInfo,2:i32 startIndex, 3:i32 pageSize),
            void updateQsZl(1:string businessId,2:string businessDetailIds,3:i32 userId,4:string fileId),
            void addBusinessQszl(1:string lsh,2:string userId),
            list<businessimagedto.BusinessImageDTO> findBusinessIdAndImageIdAndBusinessTypeIdByLsh(1:string lsh),
            i32 addFileInfo(1:businessinfo.BusinessInfo businessInfo),
            list<jjtj.Jjtj> getJxrwqsList(1:string startTime,2:string endTime),
            list<businessinfo.BusinessInfo> findFileInfoByBox(1:string box),
            list<string> findNotSignCodeByBox(1:string box),
            i64 getNewLsmxListCount(1:string startTime,2:string endTime,3:i32 jjlx,4:i32 startIndex,5:i32 pageSize),
            i64 getNewDamxListCount(1:string startTime,2:string endTime,3:i32 jjlx,4:i32 startIndex,5:i32 pageSize)
	}