package com.igool.ssp.web.model;

import java.io.Serializable;

/**
 * Created by zhanggen on 2017/8/7.
 */
public class SspParams implements Serializable{
    private Integer offerId;
    private String regionId;
    private Integer type;
    private Integer category;
    private Integer grade;
    private Integer roadType;
    private String lawType;
    private String carNum;
    private String queryStartTime;
    private String queryEndTime;
    private String queryStartCheckTime;
    private String queryEndCheckTime;
    private String userName;
    private String timeType;
    private String exported;

    public Integer getOfferId() {
        if(offerId==null)
            return -1;
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Integer getType() {
        if(type==null)
            return -1;
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        if(category==null)
            return -1;
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getGrade() {
        if(grade==null)
            return -1;
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getRoadType() {
        if(roadType==null)
            return -1;
        return roadType;
    }

    public void setRoadType(Integer roadType) {
        this.roadType = roadType;
    }

    public String getLawType() {
        return lawType;
    }

    public void setLawType(String lawType) {
        this.lawType = lawType;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getQueryStartTime() {
        return queryStartTime;
    }

    public void setQueryStartTime(String queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    public String getQueryEndTime() {
        return queryEndTime;
    }

    public void setQueryEndTime(String queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public String getQueryStartCheckTime() {
        return queryStartCheckTime;
    }

    public void setQueryStartCheckTime(String queryStartCheckTime) {
        this.queryStartCheckTime = queryStartCheckTime;
    }

    public String getQueryEndCheckTime() {
        return queryEndCheckTime;
    }

    public void setQueryEndCheckTime(String queryEndCheckTime) {
        this.queryEndCheckTime = queryEndCheckTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getExported() {
        return exported;
    }

    public void setExported(String exported) {
        this.exported = exported;
    }
}
