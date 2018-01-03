package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("SourceRoles")
public final class SourceRoles
{
    public SourceRoles() {
    }

    private int pid;

    @ThriftField(value=1, name="pid", requiredness=Requiredness.NONE)
    public int getPid() { return pid; }

    @ThriftField
    public void setPid(final int pid) { this.pid = pid; }

    private int mid;

    @ThriftField(value=2, name="mid", requiredness=Requiredness.NONE)
    public int getMid() { return mid; }

    @ThriftField
    public void setMid(final int mid) { this.mid = mid; }

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
            .add("mid", mid)
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

        SourceRoles other = (SourceRoles)o;

        return
            Objects.equals(pid, other.pid) &&
            Objects.equals(mid, other.mid) &&
            Objects.equals(rid, other.rid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            pid,
            mid,
            rid
        });
    }
}
