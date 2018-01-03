package com.igool.test.dzda.service.thrift.model;

/**
 * Created by igool on 2017/8/28.
 */
public class ImagePhotoRel {
    /**
     * 影像图片关联Id       db_column: ip_id
     */
    private Long ipId;
    /**
     * 影像id       db_column: image_id
     */
    private Integer imageId;
    /**
     * 业务配置id       db_column: btd_id
     */
    private Integer btdId;
    /**
     * 资料名称id       db_column: business_name_id
     */
    //private Integer businessNameId;
    /**
     * 图片id       db_column: photo
     */
    private String photo;
    /**
     * 排序       db_column: order_num
     */
    private Integer orderNum;
    /**
     * 图片链接	db_column: url
     */
    private String url;
    //columns END

    public Long getIpId() {
        return ipId;
    }

    public void setIpId(Long ipId) {
        this.ipId = ipId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getBtdId() {
        return btdId;
    }

    public void setBtdId(Integer btdId) {
        this.btdId = btdId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
