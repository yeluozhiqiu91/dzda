package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("OfferImage")
public final class OfferImage
{
    public OfferImage() {
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

    private int photoLoc;

    @ThriftField(value=3, name="photoLoc", requiredness=Requiredness.NONE)
    public int getPhotoLoc() { return photoLoc; }

    @ThriftField
    public void setPhotoLoc(final int photoLoc) { this.photoLoc = photoLoc; }

    private String createdAt;

    @ThriftField(value=4, name="createdAt", requiredness=Requiredness.NONE)
    public String getCreatedAt() { return createdAt; }

    @ThriftField
    public void setCreatedAt(final String createdAt) { this.createdAt = createdAt; }

    private String imageUrl;

    @ThriftField(value=5, name="imageUrl", requiredness=Requiredness.NONE)
    public String getImageUrl() { return imageUrl; }

    @ThriftField
    public void setImageUrl(final String imageUrl) { this.imageUrl = imageUrl; }

    private int imageType;

    @ThriftField(value=6, name="imageType", requiredness=Requiredness.NONE)
    public int getImageType() { return imageType; }

    @ThriftField
    public void setImageType(final int imageType) { this.imageType = imageType; }

    private String originalUrl;

    @ThriftField(value=7, name="originalUrl", requiredness=Requiredness.NONE)
    public String getOriginalUrl() { return originalUrl; }

    @ThriftField
    public void setOriginalUrl(final String originalUrl) { this.originalUrl = originalUrl; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("offerId", offerId)
            .add("photoLoc", photoLoc)
            .add("createdAt", createdAt)
            .add("imageUrl", imageUrl)
            .add("imageType", imageType)
            .add("originalUrl", originalUrl)
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

        OfferImage other = (OfferImage)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(offerId, other.offerId) &&
            Objects.equals(photoLoc, other.photoLoc) &&
            Objects.equals(createdAt, other.createdAt) &&
            Objects.equals(imageUrl, other.imageUrl) &&
            Objects.equals(imageType, other.imageType) &&
            Objects.equals(originalUrl, other.originalUrl);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            offerId,
            photoLoc,
            createdAt,
            imageUrl,
            imageType,
            originalUrl
        });
    }
}
