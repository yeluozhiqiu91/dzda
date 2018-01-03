package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("TWfldMessage")
public final class TWfldMessage
{
    public TWfldMessage() {
    }

    private String lddm;

    @ThriftField(value=1, name="lddm", requiredness=Requiredness.NONE)
    public String getLddm() { return lddm; }

    @ThriftField
    public void setLddm(final String lddm) { this.lddm = lddm; }

    private String ldmc;

    @ThriftField(value=2, name="ldmc", requiredness=Requiredness.NONE)
    public String getLdmc() { return ldmc; }

    @ThriftField
    public void setLdmc(final String ldmc) { this.ldmc = ldmc; }

    private String dldm;

    @ThriftField(value=3, name="dldm", requiredness=Requiredness.NONE)
    public String getDldm() { return dldm; }

    @ThriftField
    public void setDldm(final String dldm) { this.dldm = dldm; }

    private String dlmc;

    @ThriftField(value=4, name="dlmc", requiredness=Requiredness.NONE)
    public String getDlmc() { return dlmc; }

    @ThriftField
    public void setDlmc(final String dlmc) { this.dlmc = dlmc; }

    private String region;

    @ThriftField(value=5, name="region", requiredness=Requiredness.NONE)
    public String getRegion() { return region; }

    @ThriftField
    public void setRegion(final String region) { this.region = region; }

    private String xzqh;

    @ThriftField(value=6, name="xzqh", requiredness=Requiredness.NONE)
    public String getXzqh() { return xzqh; }

    @ThriftField
    public void setXzqh(final String xzqh) { this.xzqh = xzqh; }

    private String gxsj;

    @ThriftField(value=7, name="gxsj", requiredness=Requiredness.NONE)
    public String getGxsj() { return gxsj; }

    @ThriftField
    public void setGxsj(final String gxsj) { this.gxsj = gxsj; }

    private short ygld;

    @ThriftField(value=8, name="ygld", requiredness=Requiredness.NONE)
    public short getYgld() { return ygld; }

    @ThriftField
    public void setYgld(final short ygld) { this.ygld = ygld; }

    private String glbm;

    @ThriftField(value=9, name="glbm", requiredness=Requiredness.NONE)
    public String getGlbm() { return glbm; }

    @ThriftField
    public void setGlbm(final String glbm) { this.glbm = glbm; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("lddm", lddm)
            .add("ldmc", ldmc)
            .add("dldm", dldm)
            .add("dlmc", dlmc)
            .add("region", region)
            .add("xzqh", xzqh)
            .add("gxsj", gxsj)
            .add("ygld", ygld)
            .add("glbm", glbm)
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

        TWfldMessage other = (TWfldMessage)o;

        return
            Objects.equals(lddm, other.lddm) &&
            Objects.equals(ldmc, other.ldmc) &&
            Objects.equals(dldm, other.dldm) &&
            Objects.equals(dlmc, other.dlmc) &&
            Objects.equals(region, other.region) &&
            Objects.equals(xzqh, other.xzqh) &&
            Objects.equals(gxsj, other.gxsj) &&
            Objects.equals(ygld, other.ygld) &&
            Objects.equals(glbm, other.glbm);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            lddm,
            ldmc,
            dldm,
            dlmc,
            region,
            xzqh,
            gxsj,
            ygld,
            glbm
        });
    }
}
