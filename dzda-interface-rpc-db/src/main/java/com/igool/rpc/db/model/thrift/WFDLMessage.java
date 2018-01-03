package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("WFDLMessage")
public final class WFDLMessage
{
    public WFDLMessage() {
    }

    private String dldm;

    @ThriftField(value=1, name="dldm", requiredness=Requiredness.NONE)
    public String getDldm() { return dldm; }

    @ThriftField
    public void setDldm(final String dldm) { this.dldm = dldm; }

    private String dlmc;

    @ThriftField(value=2, name="dlmc", requiredness=Requiredness.NONE)
    public String getDlmc() { return dlmc; }

    @ThriftField
    public void setDlmc(final String dlmc) { this.dlmc = dlmc; }

    private String gxsj;

    @ThriftField(value=3, name="gxsj", requiredness=Requiredness.NONE)
    public String getGxsj() { return gxsj; }

    @ThriftField
    public void setGxsj(final String gxsj) { this.gxsj = gxsj; }

    private String xzqh;

    @ThriftField(value=4, name="xzqh", requiredness=Requiredness.NONE)
    public String getXzqh() { return xzqh; }

    @ThriftField
    public void setXzqh(final String xzqh) { this.xzqh = xzqh; }

    private String glbm;

    @ThriftField(value=5, name="glbm", requiredness=Requiredness.NONE)
    public String getGlbm() { return glbm; }

    @ThriftField
    public void setGlbm(final String glbm) { this.glbm = glbm; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("dldm", dldm)
            .add("dlmc", dlmc)
            .add("gxsj", gxsj)
            .add("xzqh", xzqh)
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

        WFDLMessage other = (WFDLMessage)o;

        return
            Objects.equals(dldm, other.dldm) &&
            Objects.equals(dlmc, other.dlmc) &&
            Objects.equals(gxsj, other.gxsj) &&
            Objects.equals(xzqh, other.xzqh) &&
            Objects.equals(glbm, other.glbm);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            dldm,
            dlmc,
            gxsj,
            xzqh,
            glbm
        });
    }
}
