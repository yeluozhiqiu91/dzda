include "dzda/imageinfo.thrift"
include "dzda/imagephotoreldto.thrift"
include "dzda/businessinfo.thrift"
namespace java com.igool.rpc.db.service.thrift

	service ImageInfoService {
	    void addOrUpdataInfoByDTO(1:imagephotoreldto.ImagePhotoRelDTO imagePhotoRelDTO),
	    void updataImageStatus(1:string lsh,2:i32 status),
	    imageinfo.BusinessImageCount findBusinessImageCount(1:string startTime,2:string endTime),
        list<imageinfo.ImageInfo> ajaxStreamNum(1:string startTime,2:string endTime),
        list<businessinfo.BusinessInfo> fileCodeDetail(1:string startTime,2:string endTime),
        list<imageinfo.UserImageCount> userImageCount(1:string startTime,2:string endTime),
        list<imageinfo.ImageInfo> businessNumDetailByDateAndUserId(1:string startTime,2:string endTime,3:string userId),
        list<businessinfo.BusinessInfo> driverNumDetail(1:string startTime,2:string endTime,3:string userId),
        i32 findImagePhotoNumByDate(1:string startTime,2:string endTime)
	}