package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("WFLDMessage")
public final class WFLDMessage
{
    public WFLDMessage() {
    }

    private String dldm;

    @ThriftField(value=1, name="dldm", requiredness=Requiredness.NONE)
    public String getDldm() { return dldm; }

    @ThriftField
    public void setDldm(final String dldm) { this.dldm = dldm; }

    private String lddm;

    @ThriftField(value=2, name="lddm", requiredness=Requiredness.NONE)
    public String getLddm() { return lddm; }

    @ThriftField
    public void setLddm(final String lddm) { this.lddm = lddm; }

    private String ldmc;

    @ThriftField(value=3, name="ldmc", requiredness=Requiredness.NONE)
    public String getLdmc() { return ldmc; }

    @ThriftField
    public void setLdmc(final String ldmc) { this.ldmc = ldmc; }

    private String gxsj;

    @ThriftField(value=4, name="gxsj", requiredness=Requiredness.NONE)
    public String getGxsj() { return gxsj; }

    @ThriftField
    public void setGxsj(final String gxsj) { this.gxsj = gxsj; }

    private String xzqh;

    @ThriftField(value=5, name="xzqh", requiredness=Requiredness.NONE)
    public String getXzqh() { return xzqh; }

    @ThriftField
    public void setXzqh(final String xzqh) { this.xzqh = xzqh; }

    private String glbm;

    @ThriftField(value=6, name="glbm", requiredness=Requiredness.NONE)
    public String getGlbm() { return glbm; }

    @ThriftField
    public void setGlbm(final String glbm) { this.glbm = glbm; }

    private int ygld;

    @ThriftField(value=7, name="ygld", requiredness=Requiredness.NONE)
    public int getYgld() { return ygld; }

    @ThriftField
    public void setYgld(final int ygld) { this.ygld = ygld; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("dldm", dldm)
            .add("lddm", lddm)
            .add("ldmc", ldmc)
            .add("gxsj", gxsj)
            .add("xzqh", xzqh)
            .add("glbm", glbm)
            .add("ygld", ygld)
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

        WFLDMessage other = (WFLDMessage)o;

        return
            Objects.equals(dldm, other.dldm) &&
            Objects.equals(lddm, other.lddm) &&
            Objects.equals(ldmc, other.ldmc) &&
            Objects.equals(gxsj, other.gxsj) &&
            Objects.equals(xzqh, other.xzqh) &&
            Objects.equals(glbm, other.glbm) &&
            Objects.equals(ygld, other.ygld);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            dldm,
            lddm,
            ldmc,
            gxsj,
            xzqh,
            glbm,
            ygld
        });
    }
}
