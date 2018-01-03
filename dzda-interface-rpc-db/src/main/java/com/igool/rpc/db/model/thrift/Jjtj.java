// Generated by swift-generator from dzda/jjtj.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Jjtj")
public final class Jjtj  implements Serializable
{
    public Jjtj() {
    }

    private int departmentId;

    @ThriftField(value=1, name="departmentId", requiredness=Requiredness.NONE)
    public int getDepartmentId() { return departmentId; }

    @ThriftField
    public void setDepartmentId(final int departmentId) { this.departmentId = departmentId; }

    private String departmentName;

    @ThriftField(value=2, name="departmentName", requiredness=Requiredness.NONE)
    public String getDepartmentName() { return departmentName; }

    @ThriftField
    public void setDepartmentName(final String departmentName) { this.departmentName = departmentName; }

    private int jjlx;

    @ThriftField(value=3, name="jjlx", requiredness=Requiredness.NONE)
    public int getJjlx() { return jjlx; }

    @ThriftField
    public void setJjlx(final int jjlx) { this.jjlx = jjlx; }

    private String jjlxStr;

    @ThriftField(value=4, name="jjlxStr", requiredness=Requiredness.NONE)
    public String getJjlxStr() { return jjlxStr; }

    @ThriftField
    public void setJjlxStr(final String jjlxStr) { this.jjlxStr = jjlxStr; }

    private int lsCount;

    @ThriftField(value=5, name="lsCount", requiredness=Requiredness.NONE)
    public int getLsCount() { return lsCount; }

    @ThriftField
    public void setLsCount(final int lsCount) { this.lsCount = lsCount; }

    private int daCount;

    @ThriftField(value=6, name="daCount", requiredness=Requiredness.NONE)
    public int getDaCount() { return daCount; }

    @ThriftField
    public void setDaCount(final int daCount) { this.daCount = daCount; }

    private int createUser;

    @ThriftField(value=7, name="createUser", requiredness=Requiredness.NONE)
    public int getCreateUser() { return createUser; }

    @ThriftField
    public void setCreateUser(final int createUser) { this.createUser = createUser; }

    private String dateTime;

    @ThriftField(value=8, name="dateTime", requiredness=Requiredness.NONE)
    public String getDateTime() { return dateTime; }

    @ThriftField
    public void setDateTime(final String dateTime) { this.dateTime = dateTime; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("departmentId", departmentId)
            .add("departmentName", departmentName)
            .add("jjlx", jjlx)
            .add("jjlxStr", jjlxStr)
            .add("lsCount", lsCount)
            .add("daCount", daCount)
            .add("createUser", createUser)
            .add("dateTime", dateTime)
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

        Jjtj other = (Jjtj)o;

        return
            Objects.equals(departmentId, other.departmentId) &&
            Objects.equals(departmentName, other.departmentName) &&
            Objects.equals(jjlx, other.jjlx) &&
            Objects.equals(jjlxStr, other.jjlxStr) &&
            Objects.equals(lsCount, other.lsCount) &&
            Objects.equals(daCount, other.daCount) &&
            Objects.equals(createUser, other.createUser) &&
            Objects.equals(dateTime, other.dateTime);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            departmentId,
            departmentName,
            jjlx,
            jjlxStr,
            lsCount,
            daCount,
            createUser,
            dateTime
        });
    }
}