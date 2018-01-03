// Generated by swift-generator from dzda/signing.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Signing")
public final class Signing  implements Serializable
{
    public Signing() {
    }

    private int signingId;

    @ThriftField(value=1, name="signingId", requiredness=Requiredness.NONE)
    public int getSigningId() { return signingId; }

    @ThriftField
    public void setSigningId(final int signingId) { this.signingId = signingId; }

    private String code;

    @ThriftField(value=2, name="code", requiredness=Requiredness.NONE)
    public String getCode() { return code; }

    @ThriftField
    public void setCode(final String code) { this.code = code; }

    private String receiverDate;

    @ThriftField(value=3, name="receiverDate", requiredness=Requiredness.NONE)
    public String getReceiverDate() { return receiverDate; }

    @ThriftField
    public void setReceiverDate(final String receiverDate) { this.receiverDate = receiverDate; }

    private int receiver;

    @ThriftField(value=4, name="receiver", requiredness=Requiredness.NONE)
    public int getReceiver() { return receiver; }

    @ThriftField
    public void setReceiver(final int receiver) { this.receiver = receiver; }

    private int status;

    @ThriftField(value=5, name="status", requiredness=Requiredness.NONE)
    public int getStatus() { return status; }

    @ThriftField
    public void setStatus(final int status) { this.status = status; }

    private String box;

    @ThriftField(value=6, name="box", requiredness=Requiredness.NONE)
    public String getBox() { return box; }

    @ThriftField
    public void setBox(final String box) { this.box = box; }

    private String createDate;

    @ThriftField(value=7, name="createDate", requiredness=Requiredness.NONE)
    public String getCreateDate() { return createDate; }

    @ThriftField
    public void setCreateDate(final String createDate) { this.createDate = createDate; }

    private int updateUser;

    @ThriftField(value=8, name="updateUser", requiredness=Requiredness.NONE)
    public int getUpdateUser() { return updateUser; }

    @ThriftField
    public void setUpdateUser(final int updateUser) { this.updateUser = updateUser; }

    private String updateDate;

    @ThriftField(value=9, name="updateDate", requiredness=Requiredness.NONE)
    public String getUpdateDate() { return updateDate; }

    @ThriftField
    public void setUpdateDate(final String updateDate) { this.updateDate = updateDate; }

    private int jjlx;

    @ThriftField(value=10, name="jjlx", requiredness=Requiredness.NONE)
    public int getJjlx() { return jjlx; }

    @ThriftField
    public void setJjlx(final int jjlx) { this.jjlx = jjlx; }

    private int count;

    @ThriftField(value=11, name="count", requiredness=Requiredness.NONE)
    public int getCount() { return count; }

    @ThriftField
    public void setCount(final int count) { this.count = count; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("signingId", signingId)
            .add("code", code)
            .add("receiverDate", receiverDate)
            .add("receiver", receiver)
            .add("status", status)
            .add("box", box)
            .add("createDate", createDate)
            .add("updateUser", updateUser)
            .add("updateDate", updateDate)
            .add("jjlx", jjlx)
            .add("count", count)
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

        Signing other = (Signing)o;

        return
            Objects.equals(signingId, other.signingId) &&
            Objects.equals(code, other.code) &&
            Objects.equals(receiverDate, other.receiverDate) &&
            Objects.equals(receiver, other.receiver) &&
            Objects.equals(status, other.status) &&
            Objects.equals(box, other.box) &&
            Objects.equals(createDate, other.createDate) &&
            Objects.equals(updateUser, other.updateUser) &&
            Objects.equals(updateDate, other.updateDate) &&
            Objects.equals(jjlx, other.jjlx) &&
            Objects.equals(count, other.count);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            signingId,
            code,
            receiverDate,
            receiver,
            status,
            box,
            createDate,
            updateUser,
            updateDate,
            jjlx,
            count
        });
    }
}
