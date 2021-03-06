// Generated by swift-generator from dzda/deliveryrel.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("DeliveryRel")
public final class DeliveryRel  implements Serializable
{
    public DeliveryRel() {
    }

    private int deliveryRelId;

    @ThriftField(value=1, name="deliveryRelId", requiredness=Requiredness.NONE)
    public int getDeliveryRelId() { return deliveryRelId; }

    @ThriftField
    public void setDeliveryRelId(final int deliveryRelId) { this.deliveryRelId = deliveryRelId; }

    private String deliveryId;

    @ThriftField(value=2, name="deliveryId", requiredness=Requiredness.NONE)
    public String getDeliveryId() { return deliveryId; }

    @ThriftField
    public void setDeliveryId(final String deliveryId) { this.deliveryId = deliveryId; }

    private int fileId;

    @ThriftField(value=3, name="fileId", requiredness=Requiredness.NONE)
    public int getFileId() { return fileId; }

    @ThriftField
    public void setFileId(final int fileId) { this.fileId = fileId; }

    private String createDate;

    @ThriftField(value=4, name="createDate", requiredness=Requiredness.NONE)
    public String getCreateDate() { return createDate; }

    @ThriftField
    public void setCreateDate(final String createDate) { this.createDate = createDate; }

    private int businessId;

    @ThriftField(value=5, name="businessId", requiredness=Requiredness.NONE)
    public int getBusinessId() { return businessId; }

    @ThriftField
    public void setBusinessId(final int businessId) { this.businessId = businessId; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("deliveryRelId", deliveryRelId)
            .add("deliveryId", deliveryId)
            .add("fileId", fileId)
            .add("createDate", createDate)
            .add("businessId", businessId)
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

        DeliveryRel other = (DeliveryRel)o;

        return
            Objects.equals(deliveryRelId, other.deliveryRelId) &&
            Objects.equals(deliveryId, other.deliveryId) &&
            Objects.equals(fileId, other.fileId) &&
            Objects.equals(createDate, other.createDate) &&
            Objects.equals(businessId, other.businessId);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            deliveryRelId,
            deliveryId,
            fileId,
            createDate,
            businessId
        });
    }
}
