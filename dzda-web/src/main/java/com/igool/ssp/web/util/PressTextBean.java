package com.igool.ssp.web.util;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xusg
 * @date 2017/5/18
 */
public class PressTextBean implements Serializable {

    private String regionId; // 区域ID

    private String validateCode; // 防伪码
    private String address; //违法地点
    private String baiduAddress; //GPS定位地点
    private String carNum; //车牌号码
    private String sspUserNum; //设备编号

    private String speed;//速度
    private String gatherTime;//采集时间
    private String gpsLongItude;//GPS经度
    private String gpsLatItude;//GPS纬度

    private boolean printImg;//是否截图图片
    private boolean image;//是否图片

    private String carCode;//号牌种类

    private Integer category;//用户类型
    private String wfdlDm;//违法道路代码
    private String wfldDm;//违法路段代码

    private String shotTime;//拍摄时间
    private Integer index;//序号

    private String policeNum;//警员编号
    private Integer imageSize;//图片数量
    private Integer videoSize;//视频数量

    private String lawCode;// 违法代码
    private String lawName;// 违法行为名称

    private Integer userId; // 举报用户编号

    public String getPoliceNum() {
        return policeNum;
    }

    public void setPoliceNum(String policeNum) {
        this.policeNum = policeNum;
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

    public Integer getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(Integer videoSize) {
        this.videoSize = videoSize;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getShotTime() {
        return shotTime;
    }

    public void setShotTime(String shotTime) {
        this.shotTime = shotTime;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getWfdlDm() {
        return wfdlDm;
    }

    public void setWfdlDm(String wfdlDm) {
        this.wfdlDm = wfdlDm;
    }

    public String getWfldDm() {
        return wfldDm;
    }

    public void setWfldDm(String wfldDm) {
        this.wfldDm = wfldDm;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public boolean getImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public boolean getPrintImg() {
        return printImg;
    }

    public void setPrintImg(boolean printImg) {
        this.printImg = printImg;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getSspUserNum() {
        return sspUserNum;
    }

    public void setSspUserNum(String sspUserNum) {
        this.sspUserNum = sspUserNum;
    }

    public String getGpsLatItude() {
        return gpsLatItude;
    }

    public void setGpsLatItude(String gpsLatItude) {
        this.gpsLatItude = gpsLatItude;
    }

    public String getGpsLongItude() {

        return gpsLongItude;
    }

    public void setGpsLongItude(String gpsLongItude) {
        this.gpsLongItude = gpsLongItude;
    }

    public String getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(String gatherTime) {
        this.gatherTime = gatherTime;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLawName() {
        return lawName;
    }

    public void setLawName(String lawName) {
        this.lawName = lawName;
    }

    public String getLawCode() {
        return lawCode;
    }

    public void setLawCode(String lawCode) {
        this.lawCode = lawCode;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBaiduAddress() {
        return baiduAddress;
    }

    public void setBaiduAddress(String baiduAddress) {
        this.baiduAddress = baiduAddress;
    }
}
