package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("TValidate")
public final class TValidate
{
    public TValidate() {
    }

    private String safecode;

    @ThriftField(value=1, name="safecode", requiredness=Requiredness.NONE)
    public String getSafecode() { return safecode; }

    @ThriftField
    public void setSafecode(final String safecode) { this.safecode = safecode; }

    private String province;

    @ThriftField(value=2, name="province", requiredness=Requiredness.NONE)
    public String getProvince() { return province; }

    @ThriftField
    public void setProvince(final String province) { this.province = province; }

    private String city;

    @ThriftField(value=3, name="city", requiredness=Requiredness.NONE)
    public String getCity() { return city; }

    @ThriftField
    public void setCity(final String city) { this.city = city; }

    private String categoryid;

    @ThriftField(value=4, name="categoryid", requiredness=Requiredness.NONE)
    public String getCategoryid() { return categoryid; }

    @ThriftField
    public void setCategoryid(final String categoryid) { this.categoryid = categoryid; }

    private int isvalidate;

    @ThriftField(value=5, name="isvalidate", requiredness=Requiredness.NONE)
    public int getIsvalidate() { return isvalidate; }

    @ThriftField
    public void setIsvalidate(final int isvalidate) { this.isvalidate = isvalidate; }

    private String starttime;

    @ThriftField(value=6, name="starttime", requiredness=Requiredness.NONE)
    public String getStarttime() { return starttime; }

    @ThriftField
    public void setStarttime(final String starttime) { this.starttime = starttime; }

    private String endtime;

    @ThriftField(value=7, name="endtime", requiredness=Requiredness.NONE)
    public String getEndtime() { return endtime; }

    @ThriftField
    public void setEndtime(final String endtime) { this.endtime = endtime; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("safecode", safecode)
            .add("province", province)
            .add("city", city)
            .add("categoryid", categoryid)
            .add("isvalidate", isvalidate)
            .add("starttime", starttime)
            .add("endtime", endtime)
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

        TValidate other = (TValidate)o;

        return
            Objects.equals(safecode, other.safecode) &&
            Objects.equals(province, other.province) &&
            Objects.equals(city, other.city) &&
            Objects.equals(categoryid, other.categoryid) &&
            Objects.equals(isvalidate, other.isvalidate) &&
            Objects.equals(starttime, other.starttime) &&
            Objects.equals(endtime, other.endtime);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            safecode,
            province,
            city,
            categoryid,
            isvalidate,
            starttime,
            endtime
        });
    }
}
