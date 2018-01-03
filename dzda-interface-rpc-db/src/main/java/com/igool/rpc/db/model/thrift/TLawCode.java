package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("TLawCode")
public final class TLawCode
{
    public TLawCode() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private int parentId;

    @ThriftField(value=2, name="parentId", requiredness=Requiredness.NONE)
    public int getParentId() { return parentId; }

    @ThriftField
    public void setParentId(final int parentId) { this.parentId = parentId; }

    private String lawName;

    @ThriftField(value=3, name="lawName", requiredness=Requiredness.NONE)
    public String getLawName() { return lawName; }

    @ThriftField
    public void setLawName(final String lawName) { this.lawName = lawName; }

    private String lawCode;

    @ThriftField(value=4, name="lawCode", requiredness=Requiredness.NONE)
    public String getLawCode() { return lawCode; }

    @ThriftField
    public void setLawCode(final String lawCode) { this.lawCode = lawCode; }

    private String remark;

    @ThriftField(value=5, name="remark", requiredness=Requiredness.NONE)
    public String getRemark() { return remark; }

    @ThriftField
    public void setRemark(final String remark) { this.remark = remark; }

    private int seqNum;

    @ThriftField(value=6, name="seqNum", requiredness=Requiredness.NONE)
    public int getSeqNum() { return seqNum; }

    @ThriftField
    public void setSeqNum(final int seqNum) { this.seqNum = seqNum; }

    private String province;

    @ThriftField(value=7, name="province", requiredness=Requiredness.NONE)
    public String getProvince() { return province; }

    @ThriftField
    public void setProvince(final String province) { this.province = province; }

    private String city;

    @ThriftField(value=8, name="city", requiredness=Requiredness.NONE)
    public String getCity() { return city; }

    @ThriftField
    public void setCity(final String city) { this.city = city; }

    private String rewardNum;

    @ThriftField(value=9, name="rewardNum", requiredness=Requiredness.NONE)
    public String getRewardNum() { return rewardNum; }

    @ThriftField
    public void setRewardNum(final String rewardNum) { this.rewardNum = rewardNum; }

    private int lawcount;

    @ThriftField(value=10, name="lawcount", requiredness=Requiredness.NONE)
    public int getLawcount() { return lawcount; }

    @ThriftField
    public void setLawcount(final int lawcount) { this.lawcount = lawcount; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("parentId", parentId)
            .add("lawName", lawName)
            .add("lawCode", lawCode)
            .add("remark", remark)
            .add("seqNum", seqNum)
            .add("province", province)
            .add("city", city)
            .add("rewardNum", rewardNum)
            .add("lawcount", lawcount)
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

        TLawCode other = (TLawCode)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(parentId, other.parentId) &&
            Objects.equals(lawName, other.lawName) &&
            Objects.equals(lawCode, other.lawCode) &&
            Objects.equals(remark, other.remark) &&
            Objects.equals(seqNum, other.seqNum) &&
            Objects.equals(province, other.province) &&
            Objects.equals(city, other.city) &&
            Objects.equals(rewardNum, other.rewardNum) &&
            Objects.equals(lawcount, other.lawcount);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            parentId,
            lawName,
            lawCode,
            remark,
            seqNum,
            province,
            city,
            rewardNum,
            lawcount
        });
    }
}
