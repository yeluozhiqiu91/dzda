include "dzda/imagephotorel.thrift"
namespace java com.igool.rpc.db.service.thrift

	service ImagePhotoRefService {
		 	void insert(1:imagephotorel.ImagePhotoRel model),
		 	list<imagephotorel.ImagePhotoRel> getImagePhotoRelList(1:i32 imageId),
		 	list<imagephotorel.ImagePhotoRel> findImageRelByLshAndBusinessTypeId(1:string lsh,2:i32 businessTypeId),
		 	void delImagePhotoRelByLshCodeAndPhotoCode(1:string lsh, 2:string photoCode),
		 	 i32 countImagePhoto()
	}