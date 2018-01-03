// Generated by swift-generator from dzda/businessinfo.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("BusinessInfo")
public final class BusinessInfo  implements Serializable
{
    public BusinessInfo() {
    }

    private int businessId;

    @ThriftField(value=1, name="businessId", requiredness=Requiredness.NONE)
    public int getBusinessId() { return businessId; }

    @ThriftField
    public void setBusinessId(final int businessId) { this.businessId = businessId; }

    private String code;

    @ThriftField(value=2, name="code", requiredness=Requiredness.NONE)
    public String getCode() { return code; }

    @ThriftField
    public void setCode(final String code) { this.code = code; }

    private String plateType;

    @ThriftField(value=3, name="plateType", requiredness=Requiredness.NONE)
    public String getPlateType() { return plateType; }

    @ThriftField
    public void setPlateType(final String plateType) { this.plateType = plateType; }

    private String plateCode;

    @ThriftField(value=4, name="plateCode", requiredness=Requiredness.NONE)
    public String getPlateCode() { return plateCode; }

    @ThriftField
    public void setPlateCode(final String plateCode) { this.plateCode = plateCode; }

    private String carCode;

    @ThriftField(value=5, name="carCode", requiredness=Requiredness.NONE)
    public String getCarCode() { return carCode; }

    @ThriftField
    public void setCarCode(final String carCode) { this.carCode = carCode; }

    private String createDate;

    @ThriftField(value=6, name="createDate", requiredness=Requiredness.NONE)
    public String getCreateDate() { return createDate; }

    @ThriftField
    public void setCreateDate(final String createDate) { this.createDate = createDate; }

    private String businessType;

    @ThriftField(value=7, name="businessType", requiredness=Requiredness.NONE)
    public String getBusinessType() { return businessType; }

    @ThriftField
    public void setBusinessType(final String businessType) { this.businessType = businessType; }

    private String applicationDate;

    @ThriftField(value=8, name="applicationDate", requiredness=Requiredness.NONE)
    public String getApplicationDate() { return applicationDate; }

    @ThriftField
    public void setApplicationDate(final String applicationDate) { this.applicationDate = applicationDate; }

    private int fileId;

    @ThriftField(value=9, name="fileId", requiredness=Requiredness.NONE)
    public int getFileId() { return fileId; }

    @ThriftField
    public void setFileId(final int fileId) { this.fileId = fileId; }

    private int qsStatus;

    @ThriftField(value=10, name="qsStatus", requiredness=Requiredness.NONE)
    public int getQsStatus() { return qsStatus; }

    @ThriftField
    public void setQsStatus(final int qsStatus) { this.qsStatus = qsStatus; }

    private String lsStatus;

    @ThriftField(value=11, name="lsStatus", requiredness=Requiredness.NONE)
    public String getLsStatus() { return lsStatus; }

    @ThriftField
    public void setLsStatus(final String lsStatus) { this.lsStatus = lsStatus; }

    private String carNumber;

    @ThriftField(value=12, name="carNumber", requiredness=Requiredness.NONE)
    public String getCarNumber() { return carNumber; }

    @ThriftField
    public void setCarNumber(final String carNumber) { this.carNumber = carNumber; }

    private int sfwd;

    @ThriftField(value=13, name="sfwd", requiredness=Requiredness.NONE)
    public int getSfwd() { return sfwd; }

    @ThriftField
    public void setSfwd(final int sfwd) { this.sfwd = sfwd; }

    private int lswz;

    @ThriftField(value=14, name="lswz", requiredness=Requiredness.NONE)
    public int getLswz() { return lswz; }

    @ThriftField
    public void setLswz(final int lswz) { this.lswz = lswz; }

    private int lszt;

    @ThriftField(value=15, name="lszt", requiredness=Requiredness.NONE)
    public int getLszt() { return lszt; }

    @ThriftField
    public void setLszt(final int lszt) { this.lszt = lszt; }

    private String box;

    @ThriftField(value=16, name="box", requiredness=Requiredness.NONE)
    public String getBox() { return box; }

    @ThriftField
    public void setBox(final String box) { this.box = box; }

    private String fileCode;

    @ThriftField(value=17, name="fileCode", requiredness=Requiredness.NONE)
    public String getFileCode() { return fileCode; }

    @ThriftField
    public void setFileCode(final String fileCode) { this.fileCode = fileCode; }

    private String plateTypeStr;

    @ThriftField(value=18, name="plateTypeStr", requiredness=Requiredness.NONE)
    public String getPlateTypeStr() { return plateTypeStr; }

    @ThriftField
    public void setPlateTypeStr(final String plateTypeStr) { this.plateTypeStr = plateTypeStr; }

    private String businessTypeStr;

    @ThriftField(value=19, name="businessTypeStr", requiredness=Requiredness.NONE)
    public String getBusinessTypeStr() { return businessTypeStr; }

    @ThriftField
    public void setBusinessTypeStr(final String businessTypeStr) { this.businessTypeStr = businessTypeStr; }

    private String imageDate;

    @ThriftField(value=20, name="imageDate", requiredness=Requiredness.NONE)
    public String getImageDate() { return imageDate; }

    @ThriftField
    public void setImageDate(final String imageDate) { this.imageDate = imageDate; }

    private int imageUser;

    @ThriftField(value=21, name="imageUser", requiredness=Requiredness.NONE)
    public int getImageUser() { return imageUser; }

    @ThriftField
    public void setImageUser(final int imageUser) { this.imageUser = imageUser; }

    private String imageUserName;

    @ThriftField(value=22, name="imageUserName", requiredness=Requiredness.NONE)
    public String getImageUserName() { return imageUserName; }

    @ThriftField
    public void setImageUserName(final String imageUserName) { this.imageUserName = imageUserName; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("businessId", businessId)
            .add("code", code)
            .add("plateType", plateType)
            .add("plateCode", plateCode)
            .add("carCode", carCode)
            .add("createDate", createDate)
            .add("businessType", businessType)
            .add("applicationDate", applicationDate)
            .add("fileId", fileId)
            .add("qsStatus", qsStatus)
            .add("lsStatus", lsStatus)
            .add("carNumber", carNumber)
            .add("sfwd", sfwd)
            .add("lswz", lswz)
            .add("lszt", lszt)
            .add("box", box)
            .add("fileCode", fileCode)
            .add("plateTypeStr", plateTypeStr)
            .add("businessTypeStr", businessTypeStr)
            .add("imageDate", imageDate)
            .add("imageUser", imageUser)
            .add("imageUserName", imageUserName)
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

        BusinessInfo other = (BusinessInfo)o;

        return
            Objects.equals(businessId, other.businessId) &&
            Objects.equals(code, other.code) &&
            Objects.equals(plateType, other.plateType) &&
            Objects.equals(plateCode, other.plateCode) &&
            Objects.equals(carCode, other.carCode) &&
            Objects.equals(createDate, other.createDate) &&
            Objects.equals(businessType, other.businessType) &&
            Objects.equals(applicationDate, other.applicationDate) &&
            Objects.equals(fileId, other.fileId) &&
            Objects.equals(qsStatus, other.qsStatus) &&
            Objects.equals(lsStatus, other.lsStatus) &&
            Objects.equals(carNumber, other.carNumber) &&
            Objects.equals(sfwd, other.sfwd) &&
            Objects.equals(lswz, other.lswz) &&
            Objects.equals(lszt, other.lszt) &&
            Objects.equals(box, other.box) &&
            Objects.equals(fileCode, other.fileCode) &&
            Objects.equals(plateTypeStr, other.plateTypeStr) &&
            Objects.equals(businessTypeStr, other.businessTypeStr) &&
            Objects.equals(imageDate, other.imageDate) &&
            Objects.equals(imageUser, other.imageUser) &&
            Objects.equals(imageUserName, other.imageUserName);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            businessId,
            code,
            plateType,
            plateCode,
            carCode,
            createDate,
            businessType,
            applicationDate,
            fileId,
            qsStatus,
            lsStatus,
            carNumber,
            sfwd,
            lswz,
            lszt,
            box,
            fileCode,
            plateTypeStr,
            businessTypeStr,
            imageDate,
            imageUser,
            imageUserName
        });
    }
}