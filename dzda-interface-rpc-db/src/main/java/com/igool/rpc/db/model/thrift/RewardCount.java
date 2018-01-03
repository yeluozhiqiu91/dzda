package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("RewardCount")
public final class RewardCount
{
    public RewardCount() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private String userName;

    @ThriftField(value=2, name="userName", requiredness=Requiredness.NONE)
    public String getUserName() { return userName; }

    @ThriftField
    public void setUserName(final String userName) { this.userName = userName; }

    private String userNum;

    @ThriftField(value=3, name="userNum", requiredness=Requiredness.NONE)
    public String getUserNum() { return userNum; }

    @ThriftField
    public void setUserNum(final String userNum) { this.userNum = userNum; }

    private String phone;

    @ThriftField(value=4, name="phone", requiredness=Requiredness.NONE)
    public String getPhone() { return phone; }

    @ThriftField
    public void setPhone(final String phone) { this.phone = phone; }

    private String sspUser;

    @ThriftField(value=5, name="sspUser", requiredness=Requiredness.NONE)
    public String getSspUser() { return sspUser; }

    @ThriftField
    public void setSspUser(final String sspUser) { this.sspUser = sspUser; }

    private String rewardNum;

    @ThriftField(value=6, name="rewardNum", requiredness=Requiredness.NONE)
    public String getRewardNum() { return rewardNum; }

    @ThriftField
    public void setRewardNum(final String rewardNum) { this.rewardNum = rewardNum; }

    private String rewardOrigion;

    @ThriftField(value=7, name="rewardOrigion", requiredness=Requiredness.NONE)
    public String getRewardOrigion() { return rewardOrigion; }

    @ThriftField
    public void setRewardOrigion(final String rewardOrigion) { this.rewardOrigion = rewardOrigion; }

    private String rewardStatus;

    @ThriftField(value=8, name="rewardStatus", requiredness=Requiredness.NONE)
    public String getRewardStatus() { return rewardStatus; }

    @ThriftField
    public void setRewardStatus(final String rewardStatus) { this.rewardStatus = rewardStatus; }

    private String actualRewardNum;

    @ThriftField(value=9, name="actualRewardNum", requiredness=Requiredness.NONE)
    public String getActualRewardNum() { return actualRewardNum; }

    @ThriftField
    public void setActualRewardNum(final String actualRewardNum) { this.actualRewardNum = actualRewardNum; }

    private String idCard;

    @ThriftField(value=10, name="idCard", requiredness=Requiredness.NONE)
    public String getIdCard() { return idCard; }

    @ThriftField
    public void setIdCard(final String idCard) { this.idCard = idCard; }

    private int provinceId;

    @ThriftField(value=11, name="provinceId", requiredness=Requiredness.NONE)
    public int getProvinceId() { return provinceId; }

    @ThriftField
    public void setProvinceId(final int provinceId) { this.provinceId = provinceId; }

    private String provinceName;

    @ThriftField(value=12, name="provinceName", requiredness=Requiredness.NONE)
    public String getProvinceName() { return provinceName; }

    @ThriftField
    public void setProvinceName(final String provinceName) { this.provinceName = provinceName; }

    private int cityId;

    @ThriftField(value=13, name="cityId", requiredness=Requiredness.NONE)
    public int getCityId() { return cityId; }

    @ThriftField
    public void setCityId(final int cityId) { this.cityId = cityId; }

    private String cityName;

    @ThriftField(value=14, name="cityName", requiredness=Requiredness.NONE)
    public String getCityName() { return cityName; }

    @ThriftField
    public void setCityName(final String cityName) { this.cityName = cityName; }

    private String areaName;

    @ThriftField(value=15, name="areaName", requiredness=Requiredness.NONE)
    public String getAreaName() { return areaName; }

    @ThriftField
    public void setAreaName(final String areaName) { this.areaName = areaName; }

    private String regionId;

    @ThriftField(value=16, name="regionId", requiredness=Requiredness.NONE)
    public String getRegionId() { return regionId; }

    @ThriftField
    public void setRegionId(final String regionId) { this.regionId = regionId; }

    private int type;

    @ThriftField(value=17, name="type", requiredness=Requiredness.NONE)
    public int getType() { return type; }

    @ThriftField
    public void setType(final int type) { this.type = type; }

    private String typeName;

    @ThriftField(value=18, name="typeName", requiredness=Requiredness.NONE)
    public String getTypeName() { return typeName; }

    @ThriftField
    public void setTypeName(final String typeName) { this.typeName = typeName; }

    private int category;

    @ThriftField(value=19, name="category", requiredness=Requiredness.NONE)
    public int getCategory() { return category; }

    @ThriftField
    public void setCategory(final int category) { this.category = category; }

    private String categoryName;

    @ThriftField(value=20, name="categoryName", requiredness=Requiredness.NONE)
    public String getCategoryName() { return categoryName; }

    @ThriftField
    public void setCategoryName(final String categoryName) { this.categoryName = categoryName; }

    private String gatherTime;

    @ThriftField(value=21, name="gatherTime", requiredness=Requiredness.NONE)
    public String getGatherTime() { return gatherTime; }

    @ThriftField
    public void setGatherTime(final String gatherTime) { this.gatherTime = gatherTime; }

    private String checkTime;

    @ThriftField(value=22, name="checkTime", requiredness=Requiredness.NONE)
    public String getCheckTime() { return checkTime; }

    @ThriftField
    public void setCheckTime(final String checkTime) { this.checkTime = checkTime; }

    private String queryStartGatherTime;

    @ThriftField(value=23, name="queryStartGatherTime", requiredness=Requiredness.NONE)
    public String getQueryStartGatherTime() { return queryStartGatherTime; }

    @ThriftField
    public void setQueryStartGatherTime(final String queryStartGatherTime) { this.queryStartGatherTime = queryStartGatherTime; }

    private String queryEndGatherTime;

    @ThriftField(value=24, name="queryEndGatherTime", requiredness=Requiredness.NONE)
    public String getQueryEndGatherTime() { return queryEndGatherTime; }

    @ThriftField
    public void setQueryEndGatherTime(final String queryEndGatherTime) { this.queryEndGatherTime = queryEndGatherTime; }

    private String queryStartCheckTime;

    @ThriftField(value=25, name="queryStartCheckTime", requiredness=Requiredness.NONE)
    public String getQueryStartCheckTime() { return queryStartCheckTime; }

    @ThriftField
    public void setQueryStartCheckTime(final String queryStartCheckTime) { this.queryStartCheckTime = queryStartCheckTime; }

    private String queryEndCheckTime;

    @ThriftField(value=26, name="queryEndCheckTime", requiredness=Requiredness.NONE)
    public String getQueryEndCheckTime() { return queryEndCheckTime; }

    @ThriftField
    public void setQueryEndCheckTime(final String queryEndCheckTime) { this.queryEndCheckTime = queryEndCheckTime; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("userName", userName)
            .add("userNum", userNum)
            .add("phone", phone)
            .add("sspUser", sspUser)
            .add("rewardNum", rewardNum)
            .add("rewardOrigion", rewardOrigion)
            .add("rewardStatus", rewardStatus)
            .add("actualRewardNum", actualRewardNum)
            .add("idCard", idCard)
            .add("provinceId", provinceId)
            .add("provinceName", provinceName)
            .add("cityId", cityId)
            .add("cityName", cityName)
            .add("areaName", areaName)
            .add("regionId", regionId)
            .add("type", type)
            .add("typeName", typeName)
            .add("category", category)
            .add("categoryName", categoryName)
            .add("gatherTime", gatherTime)
            .add("checkTime", checkTime)
            .add("queryStartGatherTime", queryStartGatherTime)
            .add("queryEndGatherTime", queryEndGatherTime)
            .add("queryStartCheckTime", queryStartCheckTime)
            .add("queryEndCheckTime", queryEndCheckTime)
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

        RewardCount other = (RewardCount)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(userName, other.userName) &&
            Objects.equals(userNum, other.userNum) &&
            Objects.equals(phone, other.phone) &&
            Objects.equals(sspUser, other.sspUser) &&
            Objects.equals(rewardNum, other.rewardNum) &&
            Objects.equals(rewardOrigion, other.rewardOrigion) &&
            Objects.equals(rewardStatus, other.rewardStatus) &&
            Objects.equals(actualRewardNum, other.actualRewardNum) &&
            Objects.equals(idCard, other.idCard) &&
            Objects.equals(provinceId, other.provinceId) &&
            Objects.equals(provinceName, other.provinceName) &&
            Objects.equals(cityId, other.cityId) &&
            Objects.equals(cityName, other.cityName) &&
            Objects.equals(areaName, other.areaName) &&
            Objects.equals(regionId, other.regionId) &&
            Objects.equals(type, other.type) &&
            Objects.equals(typeName, other.typeName) &&
            Objects.equals(category, other.category) &&
            Objects.equals(categoryName, other.categoryName) &&
            Objects.equals(gatherTime, other.gatherTime) &&
            Objects.equals(checkTime, other.checkTime) &&
            Objects.equals(queryStartGatherTime, other.queryStartGatherTime) &&
            Objects.equals(queryEndGatherTime, other.queryEndGatherTime) &&
            Objects.equals(queryStartCheckTime, other.queryStartCheckTime) &&
            Objects.equals(queryEndCheckTime, other.queryEndCheckTime);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            userName,
            userNum,
            phone,
            sspUser,
            rewardNum,
            rewardOrigion,
            rewardStatus,
            actualRewardNum,
            idCard,
            provinceId,
            provinceName,
            cityId,
            cityName,
            areaName,
            regionId,
            type,
            typeName,
            category,
            categoryName,
            gatherTime,
            checkTime,
            queryStartGatherTime,
            queryEndGatherTime,
            queryStartCheckTime,
            queryEndCheckTime
        });
    }
}
