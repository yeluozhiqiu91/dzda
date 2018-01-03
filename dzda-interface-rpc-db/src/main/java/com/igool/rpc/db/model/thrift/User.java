package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("User")
public final class User
{
    public User() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private String username;

    @ThriftField(value=2, name="username", requiredness=Requiredness.NONE)
    public String getUsername() { return username; }

    @ThriftField
    public void setUsername(final String username) { this.username = username; }

    private String realname;

    @ThriftField(value=3, name="realname", requiredness=Requiredness.NONE)
    public String getRealname() { return realname; }

    @ThriftField
    public void setRealname(final String realname) { this.realname = realname; }

    private int regionId;

    @ThriftField(value=4, name="regionId", requiredness=Requiredness.NONE)
    public int getRegionId() { return regionId; }

    @ThriftField
    public void setRegionId(final int regionId) { this.regionId = regionId; }

    private String phone;

    @ThriftField(value=5, name="phone", requiredness=Requiredness.NONE)
    public String getPhone() { return phone; }

    @ThriftField
    public void setPhone(final String phone) { this.phone = phone; }

    private String password;

    @ThriftField(value=6, name="password", requiredness=Requiredness.NONE)
    public String getPassword() { return password; }

    @ThriftField
    public void setPassword(final String password) { this.password = password; }

    private String status;

    @ThriftField(value=7, name="status", requiredness=Requiredness.NONE)
    public String getStatus() { return status; }

    @ThriftField
    public void setStatus(final String status) { this.status = status; }

    private String type;

    @ThriftField(value=8, name="type", requiredness=Requiredness.NONE)
    public String getType() { return type; }

    @ThriftField
    public void setType(final String type) { this.type = type; }

    private String createtime;

    @ThriftField(value=9, name="createtime", requiredness=Requiredness.NONE)
    public String getCreatetime() { return createtime; }

    @ThriftField
    public void setCreatetime(final String createtime) { this.createtime = createtime; }

    private String userpurtype;

    @ThriftField(value=10, name="userpurtype", requiredness=Requiredness.NONE)
    public String getUserpurtype() { return userpurtype; }

    @ThriftField
    public void setUserpurtype(final String userpurtype) { this.userpurtype = userpurtype; }

    private String province;

    @ThriftField(value=11, name="province", requiredness=Requiredness.NONE)
    public String getProvince() { return province; }

    @ThriftField
    public void setProvince(final String province) { this.province = province; }

    private String lastupdatetime;

    @ThriftField(value=12, name="lastupdatetime", requiredness=Requiredness.NONE)
    public String getLastupdatetime() { return lastupdatetime; }

    @ThriftField
    public void setLastupdatetime(final String lastupdatetime) { this.lastupdatetime = lastupdatetime; }

    private String area;

    @ThriftField(value=13, name="area", requiredness=Requiredness.NONE)
    public String getArea() { return area; }

    @ThriftField
    public void setArea(final String area) { this.area = area; }

    private String uploadType;

    @ThriftField(value=14, name="uploadType", requiredness=Requiredness.NONE)
    public String getUploadType() { return uploadType; }

    @ThriftField
    public void setUploadType(final String uploadType) { this.uploadType = uploadType; }

    private List<Source> sourceList;

    @ThriftField(value=15, name="sourceList", requiredness=Requiredness.NONE)
    public List<Source> getSourceList() { return sourceList; }

    @ThriftField
    public void setSourceList(final List<Source> sourceList) { this.sourceList = sourceList; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("username", username)
            .add("realname", realname)
            .add("regionId", regionId)
            .add("phone", phone)
            .add("password", password)
            .add("status", status)
            .add("type", type)
            .add("createtime", createtime)
            .add("userpurtype", userpurtype)
            .add("province", province)
            .add("lastupdatetime", lastupdatetime)
            .add("area", area)
            .add("uploadType", uploadType)
            .add("sourceList", sourceList)
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

        User other = (User)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(username, other.username) &&
            Objects.equals(realname, other.realname) &&
            Objects.equals(regionId, other.regionId) &&
            Objects.equals(phone, other.phone) &&
            Objects.equals(password, other.password) &&
            Objects.equals(status, other.status) &&
            Objects.equals(type, other.type) &&
            Objects.equals(createtime, other.createtime) &&
            Objects.equals(userpurtype, other.userpurtype) &&
            Objects.equals(province, other.province) &&
            Objects.equals(lastupdatetime, other.lastupdatetime) &&
            Objects.equals(area, other.area) &&
            Objects.equals(uploadType, other.uploadType) &&
            Objects.equals(sourceList, other.sourceList);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            username,
            realname,
            regionId,
            phone,
            password,
            status,
            type,
            createtime,
            userpurtype,
            province,
            lastupdatetime,
            area,
            uploadType,
            sourceList
        });
    }
}
