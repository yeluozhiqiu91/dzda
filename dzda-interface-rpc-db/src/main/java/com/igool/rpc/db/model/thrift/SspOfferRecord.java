package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("SspOfferRecord")
public final class SspOfferRecord
{
    public SspOfferRecord() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private int userId;

    @ThriftField(value=2, name="userId", requiredness=Requiredness.NONE)
    public int getUserId() { return userId; }

    @ThriftField
    public void setUserId(final int userId) { this.userId = userId; }

    private String clientId;

    @ThriftField(value=3, name="clientId", requiredness=Requiredness.NONE)
    public String getClientId() { return clientId; }

    @ThriftField
    public void setClientId(final String clientId) { this.clientId = clientId; }

    private int type;

    @ThriftField(value=4, name="type", requiredness=Requiredness.NONE)
    public int getType() { return type; }

    @ThriftField
    public void setType(final int type) { this.type = type; }

    private String typeName;

    @ThriftField(value=5, name="typeName", requiredness=Requiredness.NONE)
    public String getTypeName() { return typeName; }

    @ThriftField
    public void setTypeName(final String typeName) { this.typeName = typeName; }

    private String gatherTime;

    @ThriftField(value=6, name="gatherTime", requiredness=Requiredness.NONE)
    public String getGatherTime() { return gatherTime; }

    @ThriftField
    public void setGatherTime(final String gatherTime) { this.gatherTime = gatherTime; }

    private String regionId;

    @ThriftField(value=7, name="regionId", requiredness=Requiredness.NONE)
    public String getRegionId() { return regionId; }

    @ThriftField
    public void setRegionId(final String regionId) { this.regionId = regionId; }

    private String address;

    @ThriftField(value=8, name="address", requiredness=Requiredness.NONE)
    public String getAddress() { return address; }

    @ThriftField
    public void setAddress(final String address) { this.address = address; }

    private String gpsLongItude;

    @ThriftField(value=9, name="gpsLongItude", requiredness=Requiredness.NONE)
    public String getGpsLongItude() { return gpsLongItude; }

    @ThriftField
    public void setGpsLongItude(final String gpsLongItude) { this.gpsLongItude = gpsLongItude; }

    private String gpsLatItude;

    @ThriftField(value=10, name="gpsLatItude", requiredness=Requiredness.NONE)
    public String getGpsLatItude() { return gpsLatItude; }

    @ThriftField
    public void setGpsLatItude(final String gpsLatItude) { this.gpsLatItude = gpsLatItude; }

    private String carCode;

    @ThriftField(value=11, name="carCode", requiredness=Requiredness.NONE)
    public String getCarCode() { return carCode; }

    @ThriftField
    public void setCarCode(final String carCode) { this.carCode = carCode; }

    private String lawType;

    @ThriftField(value=12, name="lawType", requiredness=Requiredness.NONE)
    public String getLawType() { return lawType; }

    @ThriftField
    public void setLawType(final String lawType) { this.lawType = lawType; }

    private String lawCode;

    @ThriftField(value=13, name="lawCode", requiredness=Requiredness.NONE)
    public String getLawCode() { return lawCode; }

    @ThriftField
    public void setLawCode(final String lawCode) { this.lawCode = lawCode; }

    private String parentId;

    @ThriftField(value=14, name="parentId", requiredness=Requiredness.NONE)
    public String getParentId() { return parentId; }

    @ThriftField
    public void setParentId(final String parentId) { this.parentId = parentId; }

    private String carType;

    @ThriftField(value=15, name="carType", requiredness=Requiredness.NONE)
    public String getCarType() { return carType; }

    @ThriftField
    public void setCarType(final String carType) { this.carType = carType; }

    private String fzjg;

    @ThriftField(value=16, name="fzjg", requiredness=Requiredness.NONE)
    public String getFzjg() { return fzjg; }

    @ThriftField
    public void setFzjg(final String fzjg) { this.fzjg = fzjg; }

    private String wfdlDm;

    @ThriftField(value=17, name="wfdlDm", requiredness=Requiredness.NONE)
    public String getWfdlDm() { return wfdlDm; }

    @ThriftField
    public void setWfdlDm(final String wfdlDm) { this.wfdlDm = wfdlDm; }

    private String wfldDm;

    @ThriftField(value=18, name="wfldDm", requiredness=Requiredness.NONE)
    public String getWfldDm() { return wfldDm; }

    @ThriftField
    public void setWfldDm(final String wfldDm) { this.wfldDm = wfldDm; }

    private String remark;

    @ThriftField(value=19, name="remark", requiredness=Requiredness.NONE)
    public String getRemark() { return remark; }

    @ThriftField
    public void setRemark(final String remark) { this.remark = remark; }

    private String checkUserId;

    @ThriftField(value=20, name="checkUserId", requiredness=Requiredness.NONE)
    public String getCheckUserId() { return checkUserId; }

    @ThriftField
    public void setCheckUserId(final String checkUserId) { this.checkUserId = checkUserId; }

    private String checkTime;

    @ThriftField(value=21, name="checkTime", requiredness=Requiredness.NONE)
    public String getCheckTime() { return checkTime; }

    @ThriftField
    public void setCheckTime(final String checkTime) { this.checkTime = checkTime; }

    private String checkAdvice;

    @ThriftField(value=22, name="checkAdvice", requiredness=Requiredness.NONE)
    public String getCheckAdvice() { return checkAdvice; }

    @ThriftField
    public void setCheckAdvice(final String checkAdvice) { this.checkAdvice = checkAdvice; }

    private String checkStatus;

    @ThriftField(value=23, name="checkStatus", requiredness=Requiredness.NONE)
    public String getCheckStatus() { return checkStatus; }

    @ThriftField
    public void setCheckStatus(final String checkStatus) { this.checkStatus = checkStatus; }

    private String checkMemo;

    @ThriftField(value=24, name="checkMemo", requiredness=Requiredness.NONE)
    public String getCheckMemo() { return checkMemo; }

    @ThriftField
    public void setCheckMemo(final String checkMemo) { this.checkMemo = checkMemo; }

    private String createTime;

    @ThriftField(value=25, name="createTime", requiredness=Requiredness.NONE)
    public String getCreateTime() { return createTime; }

    @ThriftField
    public void setCreateTime(final String createTime) { this.createTime = createTime; }

    private String serialNum;

    @ThriftField(value=26, name="serialNum", requiredness=Requiredness.NONE)
    public String getSerialNum() { return serialNum; }

    @ThriftField
    public void setSerialNum(final String serialNum) { this.serialNum = serialNum; }

    private String isUpload;

    @ThriftField(value=27, name="isUpload", requiredness=Requiredness.NONE)
    public String getIsUpload() { return isUpload; }

    @ThriftField
    public void setIsUpload(final String isUpload) { this.isUpload = isUpload; }

    private String uploadTime;

    @ThriftField(value=28, name="uploadTime", requiredness=Requiredness.NONE)
    public String getUploadTime() { return uploadTime; }

    @ThriftField
    public void setUploadTime(final String uploadTime) { this.uploadTime = uploadTime; }

    private String shotTime;

    @ThriftField(value=29, name="shot_time", requiredness=Requiredness.NONE)
    public String getShotTime() { return shotTime; }

    @ThriftField
    public void setShotTime(final String shotTime) { this.shotTime = shotTime; }

    private String baiduAddress;

    @ThriftField(value=30, name="baiduAddress", requiredness=Requiredness.NONE)
    public String getBaiduAddress() { return baiduAddress; }

    @ThriftField
    public void setBaiduAddress(final String baiduAddress) { this.baiduAddress = baiduAddress; }

    private String jqid;

    @ThriftField(value=31, name="jqid", requiredness=Requiredness.NONE)
    public String getJqid() { return jqid; }

    @ThriftField
    public void setJqid(final String jqid) { this.jqid = jqid; }

    private String rewardCode;

    @ThriftField(value=32, name="rewardCode", requiredness=Requiredness.NONE)
    public String getRewardCode() { return rewardCode; }

    @ThriftField
    public void setRewardCode(final String rewardCode) { this.rewardCode = rewardCode; }

    private String rewardStatus;

    @ThriftField(value=33, name="rewardStatus", requiredness=Requiredness.NONE)
    public String getRewardStatus() { return rewardStatus; }

    @ThriftField
    public void setRewardStatus(final String rewardStatus) { this.rewardStatus = rewardStatus; }

    private String decideNum;

    @ThriftField(value=34, name="decideNum", requiredness=Requiredness.NONE)
    public String getDecideNum() { return decideNum; }

    @ThriftField
    public void setDecideNum(final String decideNum) { this.decideNum = decideNum; }

    private int isTellBack;

    @ThriftField(value=35, name="isTellBack", requiredness=Requiredness.NONE)
    public int getIsTellBack() { return isTellBack; }

    @ThriftField
    public void setIsTellBack(final int isTellBack) { this.isTellBack = isTellBack; }

    private String roadName;

    @ThriftField(value=36, name="roadName", requiredness=Requiredness.NONE)
    public String getRoadName() { return roadName; }

    @ThriftField
    public void setRoadName(final String roadName) { this.roadName = roadName; }

    private String carTypeName;

    @ThriftField(value=37, name="carTypeName", requiredness=Requiredness.NONE)
    public String getCarTypeName() { return carTypeName; }

    @ThriftField
    public void setCarTypeName(final String carTypeName) { this.carTypeName = carTypeName; }

    private String offer;

    @ThriftField(value=38, name="offer", requiredness=Requiredness.NONE)
    public String getOffer() { return offer; }

    @ThriftField
    public void setOffer(final String offer) { this.offer = offer; }

    private String sspUserNum;

    @ThriftField(value=39, name="sspUserNum", requiredness=Requiredness.NONE)
    public String getSspUserNum() { return sspUserNum; }

    @ThriftField
    public void setSspUserNum(final String sspUserNum) { this.sspUserNum = sspUserNum; }

    private int category;

    @ThriftField(value=40, name="category", requiredness=Requiredness.NONE)
    public int getCategory() { return category; }

    @ThriftField
    public void setCategory(final int category) { this.category = category; }

    private String categoryName;

    @ThriftField(value=41, name="categoryName", requiredness=Requiredness.NONE)
    public String getCategoryName() { return categoryName; }

    @ThriftField
    public void setCategoryName(final String categoryName) { this.categoryName = categoryName; }

    private String sspUserName;

    @ThriftField(value=42, name="sspUserName", requiredness=Requiredness.NONE)
    public String getSspUserName() { return sspUserName; }

    @ThriftField
    public void setSspUserName(final String sspUserName) { this.sspUserName = sspUserName; }

    private String sspUserCard;

    @ThriftField(value=43, name="sspUserCard", requiredness=Requiredness.NONE)
    public String getSspUserCard() { return sspUserCard; }

    @ThriftField
    public void setSspUserCard(final String sspUserCard) { this.sspUserCard = sspUserCard; }

    private String sspUserPhone;

    @ThriftField(value=44, name="sspUserPhone", requiredness=Requiredness.NONE)
    public String getSspUserPhone() { return sspUserPhone; }

    @ThriftField
    public void setSspUserPhone(final String sspUserPhone) { this.sspUserPhone = sspUserPhone; }

    private int grade;

    @ThriftField(value=45, name="grade", requiredness=Requiredness.NONE)
    public int getGrade() { return grade; }

    @ThriftField
    public void setGrade(final int grade) { this.grade = grade; }

    private String gradeName;

    @ThriftField(value=46, name="gradeName", requiredness=Requiredness.NONE)
    public String getGradeName() { return gradeName; }

    @ThriftField
    public void setGradeName(final String gradeName) { this.gradeName = gradeName; }

    private String dtjtUrl;

    @ThriftField(value=47, name="dtjtUrl", requiredness=Requiredness.NONE)
    public String getDtjtUrl() { return dtjtUrl; }

    @ThriftField
    public void setDtjtUrl(final String dtjtUrl) { this.dtjtUrl = dtjtUrl; }

    private String baiduLongItude;

    @ThriftField(value=48, name="baiduLongItude", requiredness=Requiredness.NONE)
    public String getBaiduLongItude() { return baiduLongItude; }

    @ThriftField
    public void setBaiduLongItude(final String baiduLongItude) { this.baiduLongItude = baiduLongItude; }

    private String baiduLatItude;

    @ThriftField(value=49, name="baiduLatItude", requiredness=Requiredness.NONE)
    public String getBaiduLatItude() { return baiduLatItude; }

    @ThriftField
    public void setBaiduLatItude(final String baiduLatItude) { this.baiduLatItude = baiduLatItude; }

    private String sspUserOfferCount;

    @ThriftField(value=50, name="sspUserOfferCount", requiredness=Requiredness.NONE)
    public String getSspUserOfferCount() { return sspUserOfferCount; }

    @ThriftField
    public void setSspUserOfferCount(final String sspUserOfferCount) { this.sspUserOfferCount = sspUserOfferCount; }

    private String regionCode;

    @ThriftField(value=51, name="regionCode", requiredness=Requiredness.NONE)
    public String getRegionCode() { return regionCode; }

    @ThriftField
    public void setRegionCode(final String regionCode) { this.regionCode = regionCode; }

    private String araName;

    @ThriftField(value=52, name="araName", requiredness=Requiredness.NONE)
    public String getAraName() { return araName; }

    @ThriftField
    public void setAraName(final String araName) { this.araName = araName; }

    private String provinceName;

    @ThriftField(value=53, name="provinceName", requiredness=Requiredness.NONE)
    public String getProvinceName() { return provinceName; }

    @ThriftField
    public void setProvinceName(final String provinceName) { this.provinceName = provinceName; }

    private int roadType;

    @ThriftField(value=54, name="roadType", requiredness=Requiredness.NONE)
    public int getRoadType() { return roadType; }

    @ThriftField
    public void setRoadType(final int roadType) { this.roadType = roadType; }

    private String roadTypeName;

    @ThriftField(value=55, name="roadTypeName", requiredness=Requiredness.NONE)
    public String getRoadTypeName() { return roadTypeName; }

    @ThriftField
    public void setRoadTypeName(final String roadTypeName) { this.roadTypeName = roadTypeName; }

    private String carNum;

    @ThriftField(value=56, name="carNum", requiredness=Requiredness.NONE)
    public String getCarNum() { return carNum; }

    @ThriftField
    public void setCarNum(final String carNum) { this.carNum = carNum; }

    private String cityName;

    @ThriftField(value=57, name="cityName", requiredness=Requiredness.NONE)
    public String getCityName() { return cityName; }

    @ThriftField
    public void setCityName(final String cityName) { this.cityName = cityName; }

    private String queryStartTime;

    @ThriftField(value=58, name="queryStartTime", requiredness=Requiredness.NONE)
    public String getQueryStartTime() { return queryStartTime; }

    @ThriftField
    public void setQueryStartTime(final String queryStartTime) { this.queryStartTime = queryStartTime; }

    private String queryEndTime;

    @ThriftField(value=59, name="queryEndTime", requiredness=Requiredness.NONE)
    public String getQueryEndTime() { return queryEndTime; }

    @ThriftField
    public void setQueryEndTime(final String queryEndTime) { this.queryEndTime = queryEndTime; }

    private String queryStartCheckTime;

    @ThriftField(value=60, name="queryStartCheckTime", requiredness=Requiredness.NONE)
    public String getQueryStartCheckTime() { return queryStartCheckTime; }

    @ThriftField
    public void setQueryStartCheckTime(final String queryStartCheckTime) { this.queryStartCheckTime = queryStartCheckTime; }

    private String queryEndCheckTime;

    @ThriftField(value=61, name="queryEndCheckTime", requiredness=Requiredness.NONE)
    public String getQueryEndCheckTime() { return queryEndCheckTime; }

    @ThriftField
    public void setQueryEndCheckTime(final String queryEndCheckTime) { this.queryEndCheckTime = queryEndCheckTime; }

    private int trdType;

    @ThriftField(value=62, name="trdType", requiredness=Requiredness.NONE)
    public int getTrdType() { return trdType; }

    @ThriftField
    public void setTrdType(final int trdType) { this.trdType = trdType; }

    private String checkUserName;

    @ThriftField(value=63, name="checkUserName", requiredness=Requiredness.NONE)
    public String getCheckUserName() { return checkUserName; }

    @ThriftField
    public void setCheckUserName(final String checkUserName) { this.checkUserName = checkUserName; }

    private String exported;

    @ThriftField(value=64, name="exported", requiredness=Requiredness.NONE)
    public String getExported() { return exported; }

    @ThriftField
    public void setExported(final String exported) { this.exported = exported; }

    private String testUser;

    @ThriftField(value=65, name="testUser", requiredness=Requiredness.NONE)
    public String getTestUser() { return testUser; }

    @ThriftField
    public void setTestUser(final String testUser) { this.testUser = testUser; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("userId", userId)
            .add("clientId", clientId)
            .add("type", type)
            .add("typeName", typeName)
            .add("gatherTime", gatherTime)
            .add("regionId", regionId)
            .add("address", address)
            .add("gpsLongItude", gpsLongItude)
            .add("gpsLatItude", gpsLatItude)
            .add("carCode", carCode)
            .add("lawType", lawType)
            .add("lawCode", lawCode)
            .add("parentId", parentId)
            .add("carType", carType)
            .add("fzjg", fzjg)
            .add("wfdlDm", wfdlDm)
            .add("wfldDm", wfldDm)
            .add("remark", remark)
            .add("checkUserId", checkUserId)
            .add("checkTime", checkTime)
            .add("checkAdvice", checkAdvice)
            .add("checkStatus", checkStatus)
            .add("checkMemo", checkMemo)
            .add("createTime", createTime)
            .add("serialNum", serialNum)
            .add("isUpload", isUpload)
            .add("uploadTime", uploadTime)
            .add("shotTime", shotTime)
            .add("baiduAddress", baiduAddress)
            .add("jqid", jqid)
            .add("rewardCode", rewardCode)
            .add("rewardStatus", rewardStatus)
            .add("decideNum", decideNum)
            .add("isTellBack", isTellBack)
            .add("roadName", roadName)
            .add("carTypeName", carTypeName)
            .add("offer", offer)
            .add("sspUserNum", sspUserNum)
            .add("category", category)
            .add("categoryName", categoryName)
            .add("sspUserName", sspUserName)
            .add("sspUserCard", sspUserCard)
            .add("sspUserPhone", sspUserPhone)
            .add("grade", grade)
            .add("gradeName", gradeName)
            .add("dtjtUrl", dtjtUrl)
            .add("baiduLongItude", baiduLongItude)
            .add("baiduLatItude", baiduLatItude)
            .add("sspUserOfferCount", sspUserOfferCount)
            .add("regionCode", regionCode)
            .add("araName", araName)
            .add("provinceName", provinceName)
            .add("roadType", roadType)
            .add("roadTypeName", roadTypeName)
            .add("carNum", carNum)
            .add("cityName", cityName)
            .add("queryStartTime", queryStartTime)
            .add("queryEndTime", queryEndTime)
            .add("queryStartCheckTime", queryStartCheckTime)
            .add("queryEndCheckTime", queryEndCheckTime)
            .add("trdType", trdType)
            .add("checkUserName", checkUserName)
            .add("exported", exported)
            .add("testUser", testUser)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SspOfferRecord other = (SspOfferRecord)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(userId, other.userId) &&
            Objects.equals(clientId, other.clientId) &&
            Objects.equals(type, other.type) &&
            Objects.equals(typeName, other.typeName) &&
            Objects.equals(gatherTime, other.gatherTime) &&
            Objects.equals(regionId, other.regionId) &&
            Objects.equals(address, other.address) &&
            Objects.equals(gpsLongItude, other.gpsLongItude) &&
            Objects.equals(gpsLatItude, other.gpsLatItude) &&
            Objects.equals(carCode, other.carCode) &&
            Objects.equals(lawType, other.lawType) &&
            Objects.equals(lawCode, other.lawCode) &&
            Objects.equals(parentId, other.parentId) &&
            Objects.equals(carType, other.carType) &&
            Objects.equals(fzjg, other.fzjg) &&
            Objects.equals(wfdlDm, other.wfdlDm) &&
            Objects.equals(wfldDm, other.wfldDm) &&
            Objects.equals(remark, other.remark) &&
            Objects.equals(checkUserId, other.checkUserId) &&
            Objects.equals(checkTime, other.checkTime) &&
            Objects.equals(checkAdvice, other.checkAdvice) &&
            Objects.equals(checkStatus, other.checkStatus) &&
            Objects.equals(checkMemo, other.checkMemo) &&
            Objects.equals(createTime, other.createTime) &&
            Objects.equals(serialNum, other.serialNum) &&
            Objects.equals(isUpload, other.isUpload) &&
            Objects.equals(uploadTime, other.uploadTime) &&
            Objects.equals(shotTime, other.shotTime) &&
            Objects.equals(baiduAddress, other.baiduAddress) &&
            Objects.equals(jqid, other.jqid) &&
            Objects.equals(rewardCode, other.rewardCode) &&
            Objects.equals(rewardStatus, other.rewardStatus) &&
            Objects.equals(decideNum, other.decideNum) &&
            Objects.equals(isTellBack, other.isTellBack) &&
            Objects.equals(roadName, other.roadName) &&
            Objects.equals(carTypeName, other.carTypeName) &&
            Objects.equals(offer, other.offer) &&
            Objects.equals(sspUserNum, other.sspUserNum) &&
            Objects.equals(category, other.category) &&
            Objects.equals(categoryName, other.categoryName) &&
            Objects.equals(sspUserName, other.sspUserName) &&
            Objects.equals(sspUserCard, other.sspUserCard) &&
            Objects.equals(sspUserPhone, other.sspUserPhone) &&
            Objects.equals(grade, other.grade) &&
            Objects.equals(gradeName, other.gradeName) &&
            Objects.equals(dtjtUrl, other.dtjtUrl) &&
            Objects.equals(baiduLongItude, other.baiduLongItude) &&
            Objects.equals(baiduLatItude, other.baiduLatItude) &&
            Objects.equals(sspUserOfferCount, other.sspUserOfferCount) &&
            Objects.equals(regionCode, other.regionCode) &&
            Objects.equals(araName, other.araName) &&
            Objects.equals(provinceName, other.provinceName) &&
            Objects.equals(roadType, other.roadType) &&
            Objects.equals(roadTypeName, other.roadTypeName) &&
            Objects.equals(carNum, other.carNum) &&
            Objects.equals(cityName, other.cityName) &&
            Objects.equals(queryStartTime, other.queryStartTime) &&
            Objects.equals(queryEndTime, other.queryEndTime) &&
            Objects.equals(queryStartCheckTime, other.queryStartCheckTime) &&
            Objects.equals(queryEndCheckTime, other.queryEndCheckTime) &&
            Objects.equals(trdType, other.trdType) &&
            Objects.equals(checkUserName, other.checkUserName) &&
            Objects.equals(exported, other.exported) &&
            Objects.equals(testUser, other.testUser);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            userId,
            clientId,
            type,
            typeName,
            gatherTime,
            regionId,
            address,
            gpsLongItude,
            gpsLatItude,
            carCode,
            lawType,
            lawCode,
            parentId,
            carType,
            fzjg,
            wfdlDm,
            wfldDm,
            remark,
            checkUserId,
            checkTime,
            checkAdvice,
            checkStatus,
            checkMemo,
            createTime,
            serialNum,
            isUpload,
            uploadTime,
            shotTime,
            baiduAddress,
            jqid,
            rewardCode,
            rewardStatus,
            decideNum,
            isTellBack,
            roadName,
            carTypeName,
            offer,
            sspUserNum,
            category,
            categoryName,
            sspUserName,
            sspUserCard,
            sspUserPhone,
            grade,
            gradeName,
            dtjtUrl,
            baiduLongItude,
            baiduLatItude,
            sspUserOfferCount,
            regionCode,
            araName,
            provinceName,
            roadType,
            roadTypeName,
            carNum,
            cityName,
            queryStartTime,
            queryEndTime,
            queryStartCheckTime,
            queryEndCheckTime,
            trdType,
            checkUserName,
            exported,
            testUser
        });
    }
}
