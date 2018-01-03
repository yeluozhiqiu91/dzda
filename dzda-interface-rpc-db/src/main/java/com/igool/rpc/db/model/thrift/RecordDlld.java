package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("RecordDlld")
public final class RecordDlld
{
    public RecordDlld() {
    }

    private long id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public long getId() { return id; }

    @ThriftField
    public void setId(final long id) { this.id = id; }

    private String dlbm;

    @ThriftField(value=2, name="dlbm", requiredness=Requiredness.NONE)
    public String getDlbm() { return dlbm; }

    @ThriftField
    public void setDlbm(final String dlbm) { this.dlbm = dlbm; }

    private String ldbm;

    @ThriftField(value=3, name="ldbm", requiredness=Requiredness.NONE)
    public String getLdbm() { return ldbm; }

    @ThriftField
    public void setLdbm(final String ldbm) { this.ldbm = ldbm; }

    private String dlmc;

    @ThriftField(value=4, name="dlmc", requiredness=Requiredness.NONE)
    public String getDlmc() { return dlmc; }

    @ThriftField
    public void setDlmc(final String dlmc) { this.dlmc = dlmc; }

    private int type;

    @ThriftField(value=5, name="type", requiredness=Requiredness.NONE)
    public int getType() { return type; }

    @ThriftField
    public void setType(final int type) { this.type = type; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("dlbm", dlbm)
            .add("ldbm", ldbm)
            .add("dlmc", dlmc)
            .add("type", type)
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

        RecordDlld other = (RecordDlld)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(dlbm, other.dlbm) &&
            Objects.equals(ldbm, other.ldbm) &&
            Objects.equals(dlmc, other.dlmc) &&
            Objects.equals(type, other.type);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            dlbm,
            ldbm,
            dlmc,
            type
        });
    }
}
