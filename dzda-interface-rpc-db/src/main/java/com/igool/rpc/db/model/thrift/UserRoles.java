package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("UserRoles")
public final class UserRoles
{
    public UserRoles() {
    }

    private int pid;

    @ThriftField(value=1, name="pid", requiredness=Requiredness.NONE)
    public int getPid() { return pid; }

    @ThriftField
    public void setPid(final int pid) { this.pid = pid; }

    private int aid;

    @ThriftField(value=2, name="aid", requiredness=Requiredness.NONE)
    public int getAid() { return aid; }

    @ThriftField
    public void setAid(final int aid) { this.aid = aid; }

    private int rid;

    @ThriftField(value=3, name="rid", requiredness=Requiredness.NONE)
    public int getRid() { return rid; }

    @ThriftField
    public void setRid(final int rid) { this.rid = rid; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("pid", pid)
            .add("aid", aid)
            .add("rid", rid)
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

        UserRoles other = (UserRoles)o;

        return
            Objects.equals(pid, other.pid) &&
            Objects.equals(aid, other.aid) &&
            Objects.equals(rid, other.rid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            pid,
            aid,
            rid
        });
    }
}
