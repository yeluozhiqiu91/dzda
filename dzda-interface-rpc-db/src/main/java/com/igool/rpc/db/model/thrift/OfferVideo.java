package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("OfferVideo")
public final class OfferVideo
{
    public OfferVideo() {
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

    private String createAt;

    @ThriftField(value=3, name="createAt", requiredness=Requiredness.NONE)
    public String getCreateAt() { return createAt; }

    @ThriftField
    public void setCreateAt(final String createAt) { this.createAt = createAt; }

    private String videoName;

    @ThriftField(value=4, name="videoName", requiredness=Requiredness.NONE)
    public String getVideoName() { return videoName; }

    @ThriftField
    public void setVideoName(final String videoName) { this.videoName = videoName; }

    private String videoUrl;

    @ThriftField(value=5, name="videoUrl", requiredness=Requiredness.NONE)
    public String getVideoUrl() { return videoUrl; }

    @ThriftField
    public void setVideoUrl(final String videoUrl) { this.videoUrl = videoUrl; }

    private String originalUrl;

    @ThriftField(value=6, name="originalUrl", requiredness=Requiredness.NONE)
    public String getOriginalUrl() { return originalUrl; }

    @ThriftField
    public void setOriginalUrl(final String originalUrl) { this.originalUrl = originalUrl; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("offerId", offerId)
            .add("createAt", createAt)
            .add("videoName", videoName)
            .add("videoUrl", videoUrl)
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

        OfferVideo other = (OfferVideo)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(offerId, other.offerId) &&
            Objects.equals(createAt, other.createAt) &&
            Objects.equals(videoName, other.videoName) &&
            Objects.equals(videoUrl, other.videoUrl) &&
            Objects.equals(originalUrl, other.originalUrl);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            offerId,
            createAt,
            videoName,
            videoUrl,
            originalUrl
        });
    }
}
