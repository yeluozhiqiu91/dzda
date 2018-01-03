// Generated by swift-generator from dzda/vehicle.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Vehicle")
public final class Vehicle  implements Serializable
{
    public Vehicle() {
    }

    private String dabh;

    @ThriftField(value=1, name="dabh", requiredness=Requiredness.NONE)
    public String getDabh() { return dabh; }

    @ThriftField
    public void setDabh(final String dabh) { this.dabh = dabh; }

    private String hpzl;

    @ThriftField(value=2, name="hpzl", requiredness=Requiredness.NONE)
    public String getHpzl() { return hpzl; }

    @ThriftField
    public void setHpzl(final String hpzl) { this.hpzl = hpzl; }

    private String hphm;

    @ThriftField(value=3, name="hphm", requiredness=Requiredness.NONE)
    public String getHphm() { return hphm; }

    @ThriftField
    public void setHphm(final String hphm) { this.hphm = hphm; }

    private String clsbdh;

    @ThriftField(value=4, name="clsbdh", requiredness=Requiredness.NONE)
    public String getClsbdh() { return clsbdh; }

    @ThriftField
    public void setClsbdh(final String clsbdh) { this.clsbdh = clsbdh; }

    private String syr;

    @ThriftField(value=5, name="syr", requiredness=Requiredness.NONE)
    public String getSyr() { return syr; }

    @ThriftField
    public void setSyr(final String syr) { this.syr = syr; }

    private String ccdjrq;

    @ThriftField(value=6, name="ccdjrq", requiredness=Requiredness.NONE)
    public String getCcdjrq() { return ccdjrq; }

    @ThriftField
    public void setCcdjrq(final String ccdjrq) { this.ccdjrq = ccdjrq; }

    private String xh;

    @ThriftField(value=7, name="xh", requiredness=Requiredness.NONE)
    public String getXh() { return xh; }

    @ThriftField
    public void setXh(final String xh) { this.xh = xh; }

    private String clpp1;

    @ThriftField(value=8, name="clpp1", requiredness=Requiredness.NONE)
    public String getClpp1() { return clpp1; }

    @ThriftField
    public void setClpp1(final String clpp1) { this.clpp1 = clpp1; }

    private String sfzmhm;

    @ThriftField(value=9, name="sfzmhm", requiredness=Requiredness.NONE)
    public String getSfzmhm() { return sfzmhm; }

    @ThriftField
    public void setSfzmhm(final String sfzmhm) { this.sfzmhm = sfzmhm; }

    private int isLet;

    @ThriftField(value=10, name="isLet", requiredness=Requiredness.NONE)
    public int getIsLet() { return isLet; }

    @ThriftField
    public void setIsLet(final int isLet) { this.isLet = isLet; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("dabh", dabh)
            .add("hpzl", hpzl)
            .add("hphm", hphm)
            .add("clsbdh", clsbdh)
            .add("syr", syr)
            .add("ccdjrq", ccdjrq)
            .add("xh", xh)
            .add("clpp1", clpp1)
            .add("sfzmhm", sfzmhm)
            .add("isLet", isLet)
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

        Vehicle other = (Vehicle)o;

        return
            Objects.equals(dabh, other.dabh) &&
            Objects.equals(hpzl, other.hpzl) &&
            Objects.equals(hphm, other.hphm) &&
            Objects.equals(clsbdh, other.clsbdh) &&
            Objects.equals(syr, other.syr) &&
            Objects.equals(ccdjrq, other.ccdjrq) &&
            Objects.equals(xh, other.xh) &&
            Objects.equals(clpp1, other.clpp1) &&
            Objects.equals(sfzmhm, other.sfzmhm) &&
            Objects.equals(isLet, other.isLet);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            dabh,
            hpzl,
            hphm,
            clsbdh,
            syr,
            ccdjrq,
            xh,
            clpp1,
            sfzmhm,
            isLet
        });
    }
}