// Generated by swift-generator from dzda/deliverystatistics.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("DeliveryStatistics")
public final class DeliveryStatistics  implements Serializable
{
    public DeliveryStatistics() {
    }

    private int jjlx;

    @ThriftField(value=1, name="jjlx", requiredness=Requiredness.NONE)
    public int getJjlx() { return jjlx; }

    @ThriftField
    public void setJjlx(final int jjlx) { this.jjlx = jjlx; }

    private int lssl;

    @ThriftField(value=2, name="lssl", requiredness=Requiredness.NONE)
    public int getLssl() { return lssl; }

    @ThriftField
    public void setLssl(final int lssl) { this.lssl = lssl; }

    private int dasl;

    @ThriftField(value=3, name="dasl", requiredness=Requiredness.NONE)
    public int getDasl() { return dasl; }

    @ThriftField
    public void setDasl(final int dasl) { this.dasl = dasl; }

    private String kssj;

    @ThriftField(value=4, name="kssj", requiredness=Requiredness.NONE)
    public String getKssj() { return kssj; }

    @ThriftField
    public void setKssj(final String kssj) { this.kssj = kssj; }

    private String tjsj;

    @ThriftField(value=5, name="tjsj", requiredness=Requiredness.NONE)
    public String getTjsj() { return tjsj; }

    @ThriftField
    public void setTjsj(final String tjsj) { this.tjsj = tjsj; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("jjlx", jjlx)
            .add("lssl", lssl)
            .add("dasl", dasl)
            .add("kssj", kssj)
            .add("tjsj", tjsj)
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

        DeliveryStatistics other = (DeliveryStatistics)o;

        return
            Objects.equals(jjlx, other.jjlx) &&
            Objects.equals(lssl, other.lssl) &&
            Objects.equals(dasl, other.dasl) &&
            Objects.equals(kssj, other.kssj) &&
            Objects.equals(tjsj, other.tjsj);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            jjlx,
            lssl,
            dasl,
            kssj,
            tjsj
        });
    }
}
