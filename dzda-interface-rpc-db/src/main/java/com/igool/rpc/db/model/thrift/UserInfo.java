// Generated by swift-generator from dzda/userinfo.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("UserInfo")
public final class UserInfo  implements Serializable
{
    public UserInfo() {
    }

    private int userId;

    @ThriftField(value=1, name="userId", requiredness=Requiredness.NONE)
    public int getUserId() { return userId; }

    @ThriftField
    public void setUserId(final int userId) { this.userId = userId; }

    private String account;

    @ThriftField(value=2, name="account", requiredness=Requiredness.NONE)
    public String getAccount() { return account; }

    @ThriftField
    public void setAccount(final String account) { this.account = account; }

    private String name;

    @ThriftField(value=3, name="name", requiredness=Requiredness.NONE)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private int departmentId;

    @ThriftField(value=4, name="departmentId", requiredness=Requiredness.NONE)
    public int getDepartmentId() { return departmentId; }

    @ThriftField
    public void setDepartmentId(final int departmentId) { this.departmentId = departmentId; }

    private int isSuperAdmin;

    @ThriftField(value=5, name="isSuperAdmin", requiredness=Requiredness.NONE)
    public int getIsSuperAdmin() { return isSuperAdmin; }

    @ThriftField
    public void setIsSuperAdmin(final int isSuperAdmin) { this.isSuperAdmin = isSuperAdmin; }

    private String password;

    @ThriftField(value=6, name="password", requiredness=Requiredness.NONE)
    public String getPassword() { return password; }

    @ThriftField
    public void setPassword(final String password) { this.password = password; }

    private String idCard;

    @ThriftField(value=7, name="idCard", requiredness=Requiredness.NONE)
    public String getIdCard() { return idCard; }

    @ThriftField
    public void setIdCard(final String idCard) { this.idCard = idCard; }

    private String updateDate;

    @ThriftField(value=8, name="updateDate", requiredness=Requiredness.NONE)
    public String getUpdateDate() { return updateDate; }

    @ThriftField
    public void setUpdateDate(final String updateDate) { this.updateDate = updateDate; }

    private String lastDate;

    @ThriftField(value=9, name="lastDate", requiredness=Requiredness.NONE)
    public String getLastDate() { return lastDate; }

    @ThriftField
    public void setLastDate(final String lastDate) { this.lastDate = lastDate; }

    private int status;

    @ThriftField(value=10, name="status", requiredness=Requiredness.NONE)
    public int getStatus() { return status; }

    @ThriftField
    public void setStatus(final int status) { this.status = status; }

    private int createUser;

    @ThriftField(value=11, name="createUser", requiredness=Requiredness.NONE)
    public int getCreateUser() { return createUser; }

    @ThriftField
    public void setCreateUser(final int createUser) { this.createUser = createUser; }

    private String createDate;

    @ThriftField(value=12, name="createDate", requiredness=Requiredness.NONE)
    public String getCreateDate() { return createDate; }

    @ThriftField
    public void setCreateDate(final String createDate) { this.createDate = createDate; }

    private int delUser;

    @ThriftField(value=13, name="delUser", requiredness=Requiredness.NONE)
    public int getDelUser() { return delUser; }

    @ThriftField
    public void setDelUser(final int delUser) { this.delUser = delUser; }

    private String delDate;

    @ThriftField(value=14, name="delDate", requiredness=Requiredness.NONE)
    public String getDelDate() { return delDate; }

    @ThriftField
    public void setDelDate(final String delDate) { this.delDate = delDate; }

    private int orderNum;

    @ThriftField(value=15, name="orderNum", requiredness=Requiredness.NONE)
    public int getOrderNum() { return orderNum; }

    @ThriftField
    public void setOrderNum(final int orderNum) { this.orderNum = orderNum; }

    private List<Resource> resourceList;

    @ThriftField(value=16, name="resourceList", requiredness=Requiredness.NONE)
    public List<Resource> getResourceList() { return resourceList; }

    @ThriftField
    public void setResourceList(final List<Resource> resourceList) { this.resourceList = resourceList; }

    private String departmentName;

    @ThriftField(value=17, name="departmentName", requiredness=Requiredness.NONE)
    public String getDepartmentName() { return departmentName; }

    @ThriftField
    public void setDepartmentName(final String departmentName) { this.departmentName = departmentName; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("userId", userId)
            .add("account", account)
            .add("name", name)
            .add("departmentId", departmentId)
            .add("isSuperAdmin", isSuperAdmin)
            .add("password", password)
            .add("idCard", idCard)
            .add("updateDate", updateDate)
            .add("lastDate", lastDate)
            .add("status", status)
            .add("createUser", createUser)
            .add("createDate", createDate)
            .add("delUser", delUser)
            .add("delDate", delDate)
            .add("orderNum", orderNum)
            .add("resourceList", resourceList)
            .add("departmentName", departmentName)
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

        UserInfo other = (UserInfo)o;

        return
            Objects.equals(userId, other.userId) &&
            Objects.equals(account, other.account) &&
            Objects.equals(name, other.name) &&
            Objects.equals(departmentId, other.departmentId) &&
            Objects.equals(isSuperAdmin, other.isSuperAdmin) &&
            Objects.equals(password, other.password) &&
            Objects.equals(idCard, other.idCard) &&
            Objects.equals(updateDate, other.updateDate) &&
            Objects.equals(lastDate, other.lastDate) &&
            Objects.equals(status, other.status) &&
            Objects.equals(createUser, other.createUser) &&
            Objects.equals(createDate, other.createDate) &&
            Objects.equals(delUser, other.delUser) &&
            Objects.equals(delDate, other.delDate) &&
            Objects.equals(orderNum, other.orderNum) &&
            Objects.equals(resourceList, other.resourceList) &&
            Objects.equals(departmentName, other.departmentName);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            userId,
            account,
            name,
            departmentId,
            isSuperAdmin,
            password,
            idCard,
            updateDate,
            lastDate,
            status,
            createUser,
            createDate,
            delUser,
            delDate,
            orderNum,
            resourceList,
            departmentName
        });
    }
}
