// Generated by swift-generator from dzda/business.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Business")
public final class Business  implements Serializable
{
    public Business() {
    }

    private String id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public String getId() { return id; }

    @ThriftField
    public void setId(final String id) { this.id = id; }

    private String increment;

    @ThriftField(value=2, name="increment", requiredness=Requiredness.NONE)
    public String getIncrement() { return increment; }

    @ThriftField
    public void setIncrement(final String increment) { this.increment = increment; }

    private String number;

    @ThriftField(value=3, name="number", requiredness=Requiredness.NONE)
    public String getNumber() { return number; }

    @ThriftField
    public void setNumber(final String number) { this.number = number; }

    private String name;

    @ThriftField(value=4, name="name", requiredness=Requiredness.NONE)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private String time;

    @ThriftField(value=5, name="time", requiredness=Requiredness.NONE)
    public String getTime() { return time; }

    @ThriftField
    public void setTime(final String time) { this.time = time; }

    private String plateNo;

    @ThriftField(value=6, name="plateNo", requiredness=Requiredness.NONE)
    public String getPlateNo() { return plateNo; }

    @ThriftField
    public void setPlateNo(final String plateNo) { this.plateNo = plateNo; }

    private int isImage;

    @ThriftField(value=7, name="isImage", requiredness=Requiredness.NONE)
    public int getIsImage() { return isImage; }

    @ThriftField
    public void setIsImage(final int isImage) { this.isImage = isImage; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("increment", increment)
            .add("number", number)
            .add("name", name)
            .add("time", time)
            .add("plateNo", plateNo)
            .add("isImage", isImage)
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

        Business other = (Business)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(increment, other.increment) &&
            Objects.equals(number, other.number) &&
            Objects.equals(name, other.name) &&
            Objects.equals(time, other.time) &&
            Objects.equals(plateNo, other.plateNo) &&
            Objects.equals(isImage, other.isImage);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            increment,
            number,
            name,
            time,
            plateNo,
            isImage
        });
    }
}
