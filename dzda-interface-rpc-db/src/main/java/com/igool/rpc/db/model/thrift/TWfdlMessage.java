package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("TWfdlMessage")
public final class TWfdlMessage
{
    public TWfdlMessage() {
    }

    private String dlmc;

    @ThriftField(value=1, name="dlmc", requiredness=Requiredness.NONE)
    public String getDlmc() { return dlmc; }

    @ThriftField
    public void setDlmc(final String dlmc) { this.dlmc = dlmc; }

    private String gxsj;

    @ThriftField(value=2, name="gxsj", requiredness=Requiredness.NONE)
    public String getGxsj() { return gxsj; }

    @ThriftField
    public void setGxsj(final String gxsj) { this.gxsj = gxsj; }

    private String glbm;

    @ThriftField(value=3, name="glbm", requiredness=Requiredness.NONE)
    public String getGlbm() { return glbm; }

    @ThriftField
    public void setGlbm(final String glbm) { this.glbm = glbm; }

    private String xzqh;

    @ThriftField(value=4, name="xzqh", requiredness=Requiredness.NONE)
    public String getXzqh() { return xzqh; }

    @ThriftField
    public void setXzqh(final String xzqh) { this.xzqh = xzqh; }

    private String dldm;

    @ThriftField(value=5, name="dldm", requiredness=Requiredness.NONE)
    public String getDldm() { return dldm; }

    @ThriftField
    public void setDldm(final String dldm) { this.dldm = dldm; }

    private String region;

    @ThriftField(value=6, name="region", requiredness=Requiredness.NONE)
    public String getRegion() { return region; }

    @ThriftField
    public void setRegion(final String region) { this.region = region; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("dlmc", dlmc)
            .add("gxsj", gxsj)
            .add("glbm", glbm)
            .add("xzqh", xzqh)
            .add("dldm", dldm)
            .add("region", region)
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

        TWfdlMessage other = (TWfdlMessage)o;

        return
            Objects.equals(dlmc, other.dlmc) &&
            Objects.equals(gxsj, other.gxsj) &&
            Objects.equals(glbm, other.glbm) &&
            Objects.equals(xzqh, other.xzqh) &&
            Objects.equals(dldm, other.dldm) &&
            Objects.equals(region, other.region);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            dlmc,
            gxsj,
            glbm,
            xzqh,
            dldm,
            region
        });
    }
}
