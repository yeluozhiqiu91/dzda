package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("SSPUser")
public final class SSPUser
{
    public SSPUser() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private String userNum;

    @ThriftField(value=2, name="userNum", requiredness=Requiredness.NONE)
    public String getUserNum() { return userNum; }

    @ThriftField
    public void setUserNum(final String userNum) { this.userNum = userNum; }

    private String name;

    @ThriftField(value=3, name="name", requiredness=Requiredness.NONE)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private String regionId;

    @ThriftField(value=4, name="regionId", requiredness=Requiredness.NONE)
    public String getRegionId() { return regionId; }

    @ThriftField
    public void setRegionId(final String regionId) { this.regionId = regionId; }

    private int category;

    @ThriftField(value=5, name="category", requiredness=Requiredness.NONE)
    public int getCategory() { return category; }

    @ThriftField
    public void setCategory(final int category) { this.category = category; }

    private String phone;

    @ThriftField(value=6, name="phone", requiredness=Requiredness.NONE)
    public String getPhone() { return phone; }

    @ThriftField
    public void setPhone(final String phone) { this.phone = phone; }

    private String simNum;

    @ThriftField(value=7, name="simNum", requiredness=Requiredness.NONE)
    public String getSimNum() { return simNum; }

    @ThriftField
    public void setSimNum(final String simNum) { this.simNum = simNum; }

    private String card;

    @ThriftField(value=8, name="card", requiredness=Requiredness.NONE)
    public String getCard() { return card; }

    @ThriftField
    public void setCard(final String card) { this.card = card; }

    private int status;

    @ThriftField(value=9, name="status", requiredness=Requiredness.NONE)
    public int getStatus() { return status; }

    @ThriftField
    public void setStatus(final int status) { this.status = status; }

    private String remark;

    @ThriftField(value=10, name="remark", requiredness=Requiredness.NONE)
    public String getRemark() { return remark; }

    @ThriftField
    public void setRemark(final String remark) { this.remark = remark; }

    private String endLogin;

    @ThriftField(value=11, name="endLogin", requiredness=Requiredness.NONE)
    public String getEndLogin() { return endLogin; }

    @ThriftField
    public void setEndLogin(final String endLogin) { this.endLogin = endLogin; }

    private String endOffer;

    @ThriftField(value=12, name="endOffer", requiredness=Requiredness.NONE)
    public String getEndOffer() { return endOffer; }

    @ThriftField
    public void setEndOffer(final String endOffer) { this.endOffer = endOffer; }

    private String createdAt;

    @ThriftField(value=13, name="createdAt", requiredness=Requiredness.NONE)
    public String getCreatedAt() { return createdAt; }

    @ThriftField
    public void setCreatedAt(final String createdAt) { this.createdAt = createdAt; }

    private String updatedAt;

    @ThriftField(value=14, name="updatedAt", requiredness=Requiredness.NONE)
    public String getUpdatedAt() { return updatedAt; }

    @ThriftField
    public void setUpdatedAt(final String updatedAt) { this.updatedAt = updatedAt; }

    private int operatorId;

    @ThriftField(value=15, name="operatorId", requiredness=Requiredness.NONE)
    public int getOperatorId() { return operatorId; }

    @ThriftField
    public void setOperatorId(final int operatorId) { this.operatorId = operatorId; }

    private int seq;

    @ThriftField(value=16, name="seq", requiredness=Requiredness.NONE)
    public int getSeq() { return seq; }

    @ThriftField
    public void setSeq(final int seq) { this.seq = seq; }

    private int userGroupId;

    @ThriftField(value=17, name="userGroupId", requiredness=Requiredness.NONE)
    public int getUserGroupId() { return userGroupId; }

    @ThriftField
    public void setUserGroupId(final int userGroupId) { this.userGroupId = userGroupId; }

    private int source;

    @ThriftField(value=18, name="source", requiredness=Requiredness.NONE)
    public int getSource() { return source; }

    @ThriftField
    public void setSource(final int source) { this.source = source; }

    private String carNum;

    @ThriftField(value=19, name="car_num", requiredness=Requiredness.NONE)
    public String getCarNum() { return carNum; }

    @ThriftField
    public void setCarNum(final String carNum) { this.carNum = carNum; }

    private String isSpecial;

    @ThriftField(value=20, name="isSpecial", requiredness=Requiredness.NONE)
    public String getIsSpecial() { return isSpecial; }

    @ThriftField
    public void setIsSpecial(final String isSpecial) { this.isSpecial = isSpecial; }

    private String company;

    @ThriftField(value=21, name="company", requiredness=Requiredness.NONE)
    public String getCompany() { return company; }

    @ThriftField
    public void setCompany(final String company) { this.company = company; }

    private int grade;

    @ThriftField(value=22, name="grade", requiredness=Requiredness.NONE)
    public int getGrade() { return grade; }

    @ThriftField
    public void setGrade(final int grade) { this.grade = grade; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("userNum", userNum)
            .add("name", name)
            .add("regionId", regionId)
            .add("category", category)
            .add("phone", phone)
            .add("simNum", simNum)
            .add("card", card)
            .add("status", status)
            .add("remark", remark)
            .add("endLogin", endLogin)
            .add("endOffer", endOffer)
            .add("createdAt", createdAt)
            .add("updatedAt", updatedAt)
            .add("operatorId", operatorId)
            .add("seq", seq)
            .add("userGroupId", userGroupId)
            .add("source", source)
            .add("carNum", carNum)
            .add("isSpecial", isSpecial)
            .add("company", company)
            .add("grade", grade)
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

        SSPUser other = (SSPUser)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(userNum, other.userNum) &&
            Objects.equals(name, other.name) &&
            Objects.equals(regionId, other.regionId) &&
            Objects.equals(category, other.category) &&
            Objects.equals(phone, other.phone) &&
            Objects.equals(simNum, other.simNum) &&
            Objects.equals(card, other.card) &&
            Objects.equals(status, other.status) &&
            Objects.equals(remark, other.remark) &&
            Objects.equals(endLogin, other.endLogin) &&
            Objects.equals(endOffer, other.endOffer) &&
            Objects.equals(createdAt, other.createdAt) &&
            Objects.equals(updatedAt, other.updatedAt) &&
            Objects.equals(operatorId, other.operatorId) &&
            Objects.equals(seq, other.seq) &&
            Objects.equals(userGroupId, other.userGroupId) &&
            Objects.equals(source, other.source) &&
            Objects.equals(carNum, other.carNum) &&
            Objects.equals(isSpecial, other.isSpecial) &&
            Objects.equals(company, other.company) &&
            Objects.equals(grade, other.grade);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            userNum,
            name,
            regionId,
            category,
            phone,
            simNum,
            card,
            status,
            remark,
            endLogin,
            endOffer,
            createdAt,
            updatedAt,
            operatorId,
            seq,
            userGroupId,
            source,
            carNum,
            isSpecial,
            company,
            grade
        });
    }
}
