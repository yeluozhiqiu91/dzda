// Generated by swift-generator from dzda/resource.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Resource")
public final class Resource  implements Serializable
{
    public Resource() {
    }

    private int moduleId;

    @ThriftField(value=1, name="moduleId", requiredness=Requiredness.NONE)
    public int getModuleId() { return moduleId; }

    @ThriftField
    public void setModuleId(final int moduleId) { this.moduleId = moduleId; }

    private String name;

    @ThriftField(value=2, name="name", requiredness=Requiredness.NONE)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private String path;

    @ThriftField(value=3, name="path", requiredness=Requiredness.NONE)
    public String getPath() { return path; }

    @ThriftField
    public void setPath(final String path) { this.path = path; }

    private int parentId;

    @ThriftField(value=4, name="parentId", requiredness=Requiredness.NONE)
    public int getParentId() { return parentId; }

    @ThriftField
    public void setParentId(final int parentId) { this.parentId = parentId; }

    private String code;

    @ThriftField(value=5, name="code", requiredness=Requiredness.NONE)
    public String getCode() { return code; }

    @ThriftField
    public void setCode(final String code) { this.code = code; }

    private int orderNumber;

    @ThriftField(value=6, name="orderNumber", requiredness=Requiredness.NONE)
    public int getOrderNumber() { return orderNumber; }

    @ThriftField
    public void setOrderNumber(final int orderNumber) { this.orderNumber = orderNumber; }

    private String remarks;

    @ThriftField(value=7, name="remarks", requiredness=Requiredness.NONE)
    public String getRemarks() { return remarks; }

    @ThriftField
    public void setRemarks(final String remarks) { this.remarks = remarks; }

    private int status;

    @ThriftField(value=8, name="status", requiredness=Requiredness.NONE)
    public int getStatus() { return status; }

    @ThriftField
    public void setStatus(final int status) { this.status = status; }

    private int createUser;

    @ThriftField(value=9, name="createUser", requiredness=Requiredness.NONE)
    public int getCreateUser() { return createUser; }

    @ThriftField
    public void setCreateUser(final int createUser) { this.createUser = createUser; }

    private String createDate;

    @ThriftField(value=10, name="createDate", requiredness=Requiredness.NONE)
    public String getCreateDate() { return createDate; }

    @ThriftField
    public void setCreateDate(final String createDate) { this.createDate = createDate; }

    private int updateUser;

    @ThriftField(value=11, name="updateUser", requiredness=Requiredness.NONE)
    public int getUpdateUser() { return updateUser; }

    @ThriftField
    public void setUpdateUser(final int updateUser) { this.updateUser = updateUser; }

    private String updateDate;

    @ThriftField(value=12, name="updateDate", requiredness=Requiredness.NONE)
    public String getUpdateDate() { return updateDate; }

    @ThriftField
    public void setUpdateDate(final String updateDate) { this.updateDate = updateDate; }

    private int isSelected;

    @ThriftField(value=13, name="isSelected", requiredness=Requiredness.NONE)
    public int getIsSelected() { return isSelected; }

    @ThriftField
    public void setIsSelected(final int isSelected) { this.isSelected = isSelected; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("moduleId", moduleId)
            .add("name", name)
            .add("path", path)
            .add("parentId", parentId)
            .add("code", code)
            .add("orderNumber", orderNumber)
            .add("remarks", remarks)
            .add("status", status)
            .add("createUser", createUser)
            .add("createDate", createDate)
            .add("updateUser", updateUser)
            .add("updateDate", updateDate)
            .add("isSelected", isSelected)
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

        Resource other = (Resource)o;

        return
            Objects.equals(moduleId, other.moduleId) &&
            Objects.equals(name, other.name) &&
            Objects.equals(path, other.path) &&
            Objects.equals(parentId, other.parentId) &&
            Objects.equals(code, other.code) &&
            Objects.equals(orderNumber, other.orderNumber) &&
            Objects.equals(remarks, other.remarks) &&
            Objects.equals(status, other.status) &&
            Objects.equals(createUser, other.createUser) &&
            Objects.equals(createDate, other.createDate) &&
            Objects.equals(updateUser, other.updateUser) &&
            Objects.equals(updateDate, other.updateDate) &&
            Objects.equals(isSelected, other.isSelected);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            moduleId,
            name,
            path,
            parentId,
            code,
            orderNumber,
            remarks,
            status,
            createUser,
            createDate,
            updateUser,
            updateDate,
            isSelected
        });
    }
}
