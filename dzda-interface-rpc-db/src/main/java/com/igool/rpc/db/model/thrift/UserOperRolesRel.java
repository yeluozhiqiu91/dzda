// Generated by swift-generator from dzda/useroperrolesrel.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("UserOperRolesRel")
public final class UserOperRolesRel  implements Serializable
{
    public UserOperRolesRel() {
    }

    private int uroId;

    @ThriftField(value=1, name="uroId", requiredness=Requiredness.NONE)
    public int getUroId() { return uroId; }

    @ThriftField
    public void setUroId(final int uroId) { this.uroId = uroId; }

    private int userId;

    @ThriftField(value=2, name="userId", requiredness=Requiredness.NONE)
    public int getUserId() { return userId; }

    @ThriftField
    public void setUserId(final int userId) { this.userId = userId; }

    private int rolesId;

    @ThriftField(value=3, name="rolesId", requiredness=Requiredness.NONE)
    public int getRolesId() { return rolesId; }

    @ThriftField
    public void setRolesId(final int rolesId) { this.rolesId = rolesId; }

    private int operationId;

    @ThriftField(value=4, name="operationId", requiredness=Requiredness.NONE)
    public int getOperationId() { return operationId; }

    @ThriftField
    public void setOperationId(final int operationId) { this.operationId = operationId; }

    private String operationDate;

    @ThriftField(value=5, name="operationDate", requiredness=Requiredness.NONE)
    public String getOperationDate() { return operationDate; }

    @ThriftField
    public void setOperationDate(final String operationDate) { this.operationDate = operationDate; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("uroId", uroId)
            .add("userId", userId)
            .add("rolesId", rolesId)
            .add("operationId", operationId)
            .add("operationDate", operationDate)
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

        UserOperRolesRel other = (UserOperRolesRel)o;

        return
            Objects.equals(uroId, other.uroId) &&
            Objects.equals(userId, other.userId) &&
            Objects.equals(rolesId, other.rolesId) &&
            Objects.equals(operationId, other.operationId) &&
            Objects.equals(operationDate, other.operationDate);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            uroId,
            userId,
            rolesId,
            operationId,
            operationDate
        });
    }
}
