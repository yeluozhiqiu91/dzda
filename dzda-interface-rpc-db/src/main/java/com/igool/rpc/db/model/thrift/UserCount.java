package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("UserCount")
public final class UserCount
{
    public UserCount() {
    }

    private String provinceName;

    @ThriftField(value=1, name="provinceName", requiredness=Requiredness.NONE)
    public String getProvinceName() { return provinceName; }

    @ThriftField
    public void setProvinceName(final String provinceName) { this.provinceName = provinceName; }

    private String cityName;

    @ThriftField(value=2, name="cityName", requiredness=Requiredness.NONE)
    public String getCityName() { return cityName; }

    @ThriftField
    public void setCityName(final String cityName) { this.cityName = cityName; }

    private String userName;

    @ThriftField(value=3, name="userName", requiredness=Requiredness.NONE)
    public String getUserName() { return userName; }

    @ThriftField
    public void setUserName(final String userName) { this.userName = userName; }

    private String gatherTime;

    @ThriftField(value=4, name="gatherTime", requiredness=Requiredness.NONE)
    public String getGatherTime() { return gatherTime; }

    @ThriftField
    public void setGatherTime(final String gatherTime) { this.gatherTime = gatherTime; }

    private String queryStartGatherTime;

    @ThriftField(value=5, name="queryStartGatherTime", requiredness=Requiredness.NONE)
    public String getQueryStartGatherTime() { return queryStartGatherTime; }

    @ThriftField
    public void setQueryStartGatherTime(final String queryStartGatherTime) { this.queryStartGatherTime = queryStartGatherTime; }

    private String queryEndGatherTime;

    @ThriftField(value=6, name="queryEndGatherTime", requiredness=Requiredness.NONE)
    public String getQueryEndGatherTime() { return queryEndGatherTime; }

    @ThriftField
    public void setQueryEndGatherTime(final String queryEndGatherTime) { this.queryEndGatherTime = queryEndGatherTime; }

    private String checkTime;

    @ThriftField(value=7, name="checkTime", requiredness=Requiredness.NONE)
    public String getCheckTime() { return checkTime; }

    @ThriftField
    public void setCheckTime(final String checkTime) { this.checkTime = checkTime; }

    private long pass;

    @ThriftField(value=8, name="pass", requiredness=Requiredness.NONE)
    public long getPass() { return pass; }

    @ThriftField
    public void setPass(final long pass) { this.pass = pass; }

    private long unPass;

    @ThriftField(value=9, name="unPass", requiredness=Requiredness.NONE)
    public long getUnPass() { return unPass; }

    @ThriftField
    public void setUnPass(final long unPass) { this.unPass = unPass; }

    private long checkPass;

    @ThriftField(value=10, name="checkPass", requiredness=Requiredness.NONE)
    public long getCheckPass() { return checkPass; }

    @ThriftField
    public void setCheckPass(final long checkPass) { this.checkPass = checkPass; }

    private long unCheckPass;

    @ThriftField(value=11, name="unCheckPass", requiredness=Requiredness.NONE)
    public long getUnCheckPass() { return unCheckPass; }

    @ThriftField
    public void setUnCheckPass(final long unCheckPass) { this.unCheckPass = unCheckPass; }

    private long totalCount;

    @ThriftField(value=12, name="totalCount", requiredness=Requiredness.NONE)
    public long getTotalCount() { return totalCount; }

    @ThriftField
    public void setTotalCount(final long totalCount) { this.totalCount = totalCount; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("provinceName", provinceName)
            .add("cityName", cityName)
            .add("userName", userName)
            .add("gatherTime", gatherTime)
            .add("queryStartGatherTime", queryStartGatherTime)
            .add("queryEndGatherTime", queryEndGatherTime)
            .add("checkTime", checkTime)
            .add("pass", pass)
            .add("unPass", unPass)
            .add("checkPass", checkPass)
            .add("unCheckPass", unCheckPass)
            .add("totalCount", totalCount)
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

        UserCount other = (UserCount)o;

        return
            Objects.equals(provinceName, other.provinceName) &&
            Objects.equals(cityName, other.cityName) &&
            Objects.equals(userName, other.userName) &&
            Objects.equals(gatherTime, other.gatherTime) &&
            Objects.equals(queryStartGatherTime, other.queryStartGatherTime) &&
            Objects.equals(queryEndGatherTime, other.queryEndGatherTime) &&
            Objects.equals(checkTime, other.checkTime) &&
            Objects.equals(pass, other.pass) &&
            Objects.equals(unPass, other.unPass) &&
            Objects.equals(checkPass, other.checkPass) &&
            Objects.equals(unCheckPass, other.unCheckPass) &&
            Objects.equals(totalCount, other.totalCount);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            provinceName,
            cityName,
            userName,
            gatherTime,
            queryStartGatherTime,
            queryEndGatherTime,
            checkTime,
            pass,
            unPass,
            checkPass,
            unCheckPass,
            totalCount
        });
    }
}
