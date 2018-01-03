package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("TDictionary")
public final class TDictionary
{
    public TDictionary() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private String dname;

    @ThriftField(value=2, name="dname", requiredness=Requiredness.NONE)
    public String getDname() { return dname; }

    @ThriftField
    public void setDname(final String dname) { this.dname = dname; }

    private String dvalue;

    @ThriftField(value=3, name="dvalue", requiredness=Requiredness.NONE)
    public String getDvalue() { return dvalue; }

    @ThriftField
    public void setDvalue(final String dvalue) { this.dvalue = dvalue; }

    private String dtype;

    @ThriftField(value=4, name="dtype", requiredness=Requiredness.NONE)
    public String getDtype() { return dtype; }

    @ThriftField
    public void setDtype(final String dtype) { this.dtype = dtype; }

    private String ddiscribe;

    @ThriftField(value=5, name="ddiscribe", requiredness=Requiredness.NONE)
    public String getDdiscribe() { return ddiscribe; }

    @ThriftField
    public void setDdiscribe(final String ddiscribe) { this.ddiscribe = ddiscribe; }

    private String citycode;

    @ThriftField(value=6, name="citycode", requiredness=Requiredness.NONE)
    public String getCitycode() { return citycode; }

    @ThriftField
    public void setCitycode(final String citycode) { this.citycode = citycode; }

    private int regionid;

    @ThriftField(value=7, name="regionid", requiredness=Requiredness.NONE)
    public int getRegionid() { return regionid; }

    @ThriftField
    public void setRegionid(final int regionid) { this.regionid = regionid; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("dname", dname)
            .add("dvalue", dvalue)
            .add("dtype", dtype)
            .add("ddiscribe", ddiscribe)
            .add("citycode", citycode)
            .add("regionid", regionid)
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

        TDictionary other = (TDictionary)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(dname, other.dname) &&
            Objects.equals(dvalue, other.dvalue) &&
            Objects.equals(dtype, other.dtype) &&
            Objects.equals(ddiscribe, other.ddiscribe) &&
            Objects.equals(citycode, other.citycode) &&
            Objects.equals(regionid, other.regionid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            dname,
            dvalue,
            dtype,
            ddiscribe,
            citycode,
            regionid
        });
    }
}
