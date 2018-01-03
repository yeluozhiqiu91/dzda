package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("RegionCount")
public final class RegionCount
{
    public RegionCount() {
    }

    private int provinceId;

    @ThriftField(value=1, name="provinceId", requiredness=Requiredness.NONE)
    public int getProvinceId() { return provinceId; }

    @ThriftField
    public void setProvinceId(final int provinceId) { this.provinceId = provinceId; }

    private String provinceName;

    @ThriftField(value=2, name="provinceName", requiredness=Requiredness.NONE)
    public String getProvinceName() { return provinceName; }

    @ThriftField
    public void setProvinceName(final String provinceName) { this.provinceName = provinceName; }

    private int cityId;

    @ThriftField(value=3, name="cityId", requiredness=Requiredness.NONE)
    public int getCityId() { return cityId; }

    @ThriftField
    public void setCityId(final int cityId) { this.cityId = cityId; }

    private String cityName;

    @ThriftField(value=4, name="cityName", requiredness=Requiredness.NONE)
    public String getCityName() { return cityName; }

    @ThriftField
    public void setCityName(final String cityName) { this.cityName = cityName; }

    private String areaName;

    @ThriftField(value=5, name="areaName", requiredness=Requiredness.NONE)
    public String getAreaName() { return areaName; }

    @ThriftField
    public void setAreaName(final String areaName) { this.areaName = areaName; }

    private String regionId;

    @ThriftField(value=6, name="regionId", requiredness=Requiredness.NONE)
    public String getRegionId() { return regionId; }

    @ThriftField
    public void setRegionId(final String regionId) { this.regionId = regionId; }

    private int type;

    @ThriftField(value=7, name="type", requiredness=Requiredness.NONE)
    public int getType() { return type; }

    @ThriftField
    public void setType(final int type) { this.type = type; }

    private String typeName;

    @ThriftField(value=8, name="typeName", requiredness=Requiredness.NONE)
    public String getTypeName() { return typeName; }

    @ThriftField
    public void setTypeName(final String typeName) { this.typeName = typeName; }

    private int category;

    @ThriftField(value=9, name="category", requiredness=Requiredness.NONE)
    public int getCategory() { return category; }

    @ThriftField
    public void setCategory(final int category) { this.category = category; }

    private String categoryName;

    @ThriftField(value=10, name="categoryName", requiredness=Requiredness.NONE)
    public String getCategoryName() { return categoryName; }

    @ThriftField
    public void setCategoryName(final String categoryName) { this.categoryName = categoryName; }

    private String gatherTime;

    @ThriftField(value=11, name="gatherTime", requiredness=Requiredness.NONE)
    public String getGatherTime() { return gatherTime; }

    @ThriftField
    public void setGatherTime(final String gatherTime) { this.gatherTime = gatherTime; }

    private String checkTime;

    @ThriftField(value=12, name="checkTime", requiredness=Requiredness.NONE)
    public String getCheckTime() { return checkTime; }

    @ThriftField
    public void setCheckTime(final String checkTime) { this.checkTime = checkTime; }

    private String queryStartGatherTime;

    @ThriftField(value=13, name="queryStartGatherTime", requiredness=Requiredness.NONE)
    public String getQueryStartGatherTime() { return queryStartGatherTime; }

    @ThriftField
    public void setQueryStartGatherTime(final String queryStartGatherTime) { this.queryStartGatherTime = queryStartGatherTime; }

    private String queryEndGatherTime;

    @ThriftField(value=14, name="queryEndGatherTime", requiredness=Requiredness.NONE)
    public String getQueryEndGatherTime() { return queryEndGatherTime; }

    @ThriftField
    public void setQueryEndGatherTime(final String queryEndGatherTime) { this.queryEndGatherTime = queryEndGatherTime; }

    private String queryStartCheckTime;

    @ThriftField(value=15, name="queryStartCheckTime", requiredness=Requiredness.NONE)
    public String getQueryStartCheckTime() { return queryStartCheckTime; }

    @ThriftField
    public void setQueryStartCheckTime(final String queryStartCheckTime) { this.queryStartCheckTime = queryStartCheckTime; }

    private String queryEndCheckTime;

    @ThriftField(value=16, name="queryEndCheckTime", requiredness=Requiredness.NONE)
    public String getQueryEndCheckTime() { return queryEndCheckTime; }

    @ThriftField
    public void setQueryEndCheckTime(final String queryEndCheckTime) { this.queryEndCheckTime = queryEndCheckTime; }

    private long unCheck;

    @ThriftField(value=17, name="unCheck", requiredness=Requiredness.NONE)
    public long getUnCheck() { return unCheck; }

    @ThriftField
    public void setUnCheck(final long unCheck) { this.unCheck = unCheck; }

    private long unTest;

    @ThriftField(value=18, name="unTest", requiredness=Requiredness.NONE)
    public long getUnTest() { return unTest; }

    @ThriftField
    public void setUnTest(final long unTest) { this.unTest = unTest; }

    private long checkunPassed;

    @ThriftField(value=19, name="checkunPassed", requiredness=Requiredness.NONE)
    public long getCheckunPassed() { return checkunPassed; }

    @ThriftField
    public void setCheckunPassed(final long checkunPassed) { this.checkunPassed = checkunPassed; }

    private long testunPassed;

    @ThriftField(value=20, name="testunPassed", requiredness=Requiredness.NONE)
    public long getTestunPassed() { return testunPassed; }

    @ThriftField
    public void setTestunPassed(final long testunPassed) { this.testunPassed = testunPassed; }

    private long testPassed;

    @ThriftField(value=21, name="testPassed", requiredness=Requiredness.NONE)
    public long getTestPassed() { return testPassed; }

    @ThriftField
    public void setTestPassed(final long testPassed) { this.testPassed = testPassed; }

    private long exported;

    @ThriftField(value=22, name="exported", requiredness=Requiredness.NONE)
    public long getExported() { return exported; }

    @ThriftField
    public void setExported(final long exported) { this.exported = exported; }

    private long unExported;

    @ThriftField(value=23, name="unExported", requiredness=Requiredness.NONE)
    public long getUnExported() { return unExported; }

    @ThriftField
    public void setUnExported(final long unExported) { this.unExported = unExported; }

    private long totalCount;

    @ThriftField(value=24, name="totalCount", requiredness=Requiredness.NONE)
    public long getTotalCount() { return totalCount; }

    @ThriftField
    public void setTotalCount(final long totalCount) { this.totalCount = totalCount; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("provinceId", provinceId)
            .add("provinceName", provinceName)
            .add("cityId", cityId)
            .add("cityName", cityName)
            .add("areaName", areaName)
            .add("regionId", regionId)
            .add("type", type)
            .add("typeName", typeName)
            .add("category", category)
            .add("categoryName", categoryName)
            .add("gatherTime", gatherTime)
            .add("checkTime", checkTime)
            .add("queryStartGatherTime", queryStartGatherTime)
            .add("queryEndGatherTime", queryEndGatherTime)
            .add("queryStartCheckTime", queryStartCheckTime)
            .add("queryEndCheckTime", queryEndCheckTime)
            .add("unCheck", unCheck)
            .add("unTest", unTest)
            .add("checkunPassed", checkunPassed)
            .add("testunPassed", testunPassed)
            .add("testPassed", testPassed)
            .add("exported", exported)
            .add("unExported", unExported)
            .add("totalCount", totalCount)
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

        RegionCount other = (RegionCount)o;

        return
            Objects.equals(provinceId, other.provinceId) &&
            Objects.equals(provinceName, other.provinceName) &&
            Objects.equals(cityId, other.cityId) &&
            Objects.equals(cityName, other.cityName) &&
            Objects.equals(areaName, other.areaName) &&
            Objects.equals(regionId, other.regionId) &&
            Objects.equals(type, other.type) &&
            Objects.equals(typeName, other.typeName) &&
            Objects.equals(category, other.category) &&
            Objects.equals(categoryName, other.categoryName) &&
            Objects.equals(gatherTime, other.gatherTime) &&
            Objects.equals(checkTime, other.checkTime) &&
            Objects.equals(queryStartGatherTime, other.queryStartGatherTime) &&
            Objects.equals(queryEndGatherTime, other.queryEndGatherTime) &&
            Objects.equals(queryStartCheckTime, other.queryStartCheckTime) &&
            Objects.equals(queryEndCheckTime, other.queryEndCheckTime) &&
            Objects.equals(unCheck, other.unCheck) &&
            Objects.equals(unTest, other.unTest) &&
            Objects.equals(checkunPassed, other.checkunPassed) &&
            Objects.equals(testunPassed, other.testunPassed) &&
            Objects.equals(testPassed, other.testPassed) &&
            Objects.equals(exported, other.exported) &&
            Objects.equals(unExported, other.unExported) &&
            Objects.equals(totalCount, other.totalCount);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            provinceId,
            provinceName,
            cityId,
            cityName,
            areaName,
            regionId,
            type,
            typeName,
            category,
            categoryName,
            gatherTime,
            checkTime,
            queryStartGatherTime,
            queryEndGatherTime,
            queryStartCheckTime,
            queryEndCheckTime,
            unCheck,
            unTest,
            checkunPassed,
            testunPassed,
            testPassed,
            exported,
            unExported,
            totalCount
        });
    }
}
