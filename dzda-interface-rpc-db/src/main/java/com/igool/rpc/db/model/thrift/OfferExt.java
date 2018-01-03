package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("OfferExt")
public final class OfferExt
{
    public OfferExt() {
    }

    private int offerId;

    @ThriftField(value=1, name="offerId", requiredness=Requiredness.NONE)
    public int getOfferId() { return offerId; }

    @ThriftField
    public void setOfferId(final int offerId) { this.offerId = offerId; }

    private int dataType;

    @ThriftField(value=2, name="dataType", requiredness=Requiredness.NONE)
    public int getDataType() { return dataType; }

    @ThriftField
    public void setDataType(final int dataType) { this.dataType = dataType; }

    private int dataSubType;

    @ThriftField(value=2, name="dataSubType", requiredness=Requiredness.NONE)
    public int getDataSubType() { return dataSubType; }

    @ThriftField
    public void setDataSubType(final int dataSubType) { this.dataSubType = dataSubType; }

    private String direction;

    @ThriftField(value=4, name="direction", requiredness=Requiredness.NONE)
    public String getDirection() { return direction; }

    @ThriftField
    public void setDirection(final String direction) { this.direction = direction; }

    private String exported;

    @ThriftField(value=5, name="exported", requiredness=Requiredness.NONE)
    public String getExported() { return exported; }

    @ThriftField
    public void setExported(final String exported) { this.exported = exported; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("offerId", offerId)
            .add("dataType", dataType)
            .add("dataSubType", dataSubType)
            .add("direction", direction)
            .add("exported", exported)
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

        OfferExt other = (OfferExt)o;

        return
            Objects.equals(offerId, other.offerId) &&
            Objects.equals(dataType, other.dataType) &&
            Objects.equals(dataSubType, other.dataSubType) &&
            Objects.equals(direction, other.direction) &&
            Objects.equals(exported, other.exported);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            offerId,
            dataType,
            dataSubType,
            direction,
            exported
        });
    }
}
