package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("ScreenShotTime")
public final class ScreenShotTime
{
    public ScreenShotTime() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private int offerId;

    @ThriftField(value=2, name="offerId", requiredness=Requiredness.NONE)
    public int getOfferId() { return offerId; }

    @ThriftField
    public void setOfferId(final int offerId) { this.offerId = offerId; }

    private String pic1Time;

    @ThriftField(value=3, name="pic1Time", requiredness=Requiredness.NONE)
    public String getPic1Time() { return pic1Time; }

    @ThriftField
    public void setPic1Time(final String pic1Time) { this.pic1Time = pic1Time; }

    private String pic2Time;

    @ThriftField(value=4, name="pic2Time", requiredness=Requiredness.NONE)
    public String getPic2Time() { return pic2Time; }

    @ThriftField
    public void setPic2Time(final String pic2Time) { this.pic2Time = pic2Time; }

    private String pic3Time;

    @ThriftField(value=5, name="pic3Time", requiredness=Requiredness.NONE)
    public String getPic3Time() { return pic3Time; }

    @ThriftField
    public void setPic3Time(final String pic3Time) { this.pic3Time = pic3Time; }

    private String pic4Time;

    @ThriftField(value=6, name="pic4Time", requiredness=Requiredness.NONE)
    public String getPic4Time() { return pic4Time; }

    @ThriftField
    public void setPic4Time(final String pic4Time) { this.pic4Time = pic4Time; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("offerId", offerId)
            .add("pic1Time", pic1Time)
            .add("pic2Time", pic2Time)
            .add("pic3Time", pic3Time)
            .add("pic4Time", pic4Time)
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

        ScreenShotTime other = (ScreenShotTime)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(offerId, other.offerId) &&
            Objects.equals(pic1Time, other.pic1Time) &&
            Objects.equals(pic2Time, other.pic2Time) &&
            Objects.equals(pic3Time, other.pic3Time) &&
            Objects.equals(pic4Time, other.pic4Time);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            offerId,
            pic1Time,
            pic2Time,
            pic3Time,
            pic4Time
        });
    }
}
