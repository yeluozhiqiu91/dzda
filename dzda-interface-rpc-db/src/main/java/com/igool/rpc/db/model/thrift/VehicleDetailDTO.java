// Generated by swift-generator from dzda/vehicledetaildto.thrift

package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;
import java.io.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("VehicleDetailDTO")
public final class VehicleDetailDTO  implements Serializable
{
    public VehicleDetailDTO() {
    }

    private String name;

    @ThriftField(value=1, name="name", requiredness=Requiredness.NONE)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private String nameHide;

    @ThriftField(value=2, name="nameHide", requiredness=Requiredness.NONE)
    public String getNameHide() { return nameHide; }

    @ThriftField
    public void setNameHide(final String nameHide) { this.nameHide = nameHide; }

    private String plateNo;

    @ThriftField(value=3, name="plateNo", requiredness=Requiredness.NONE)
    public String getPlateNo() { return plateNo; }

    @ThriftField
    public void setPlateNo(final String plateNo) { this.plateNo = plateNo; }

    private String vin;

    @ThriftField(value=4, name="vin", requiredness=Requiredness.NONE)
    public String getVin() { return vin; }

    @ThriftField
    public void setVin(final String vin) { this.vin = vin; }

    private String vinHide;

    @ThriftField(value=5, name="vinHide", requiredness=Requiredness.NONE)
    public String getVinHide() { return vinHide; }

    @ThriftField
    public void setVinHide(final String vinHide) { this.vinHide = vinHide; }

    private String engineNo;

    @ThriftField(value=6, name="engineNo", requiredness=Requiredness.NONE)
    public String getEngineNo() { return engineNo; }

    @ThriftField
    public void setEngineNo(final String engineNo) { this.engineNo = engineNo; }

    private String engineNoHide;

    @ThriftField(value=7, name="engineNoHide", requiredness=Requiredness.NONE)
    public String getEngineNoHide() { return engineNoHide; }

    @ThriftField
    public void setEngineNoHide(final String engineNoHide) { this.engineNoHide = engineNoHide; }

    private String plateType;

    @ThriftField(value=8, name="plateType", requiredness=Requiredness.NONE)
    public String getPlateType() { return plateType; }

    @ThriftField
    public void setPlateType(final String plateType) { this.plateType = plateType; }

    private String vehicleType;

    @ThriftField(value=9, name="vehicleType", requiredness=Requiredness.NONE)
    public String getVehicleType() { return vehicleType; }

    @ThriftField
    public void setVehicleType(final String vehicleType) { this.vehicleType = vehicleType; }

    private String brands;

    @ThriftField(value=10, name="brands", requiredness=Requiredness.NONE)
    public String getBrands() { return brands; }

    @ThriftField
    public void setBrands(final String brands) { this.brands = brands; }

    private String model;

    @ThriftField(value=11, name="model", requiredness=Requiredness.NONE)
    public String getModel() { return model; }

    @ThriftField
    public void setModel(final String model) { this.model = model; }

    private String carColor;

    @ThriftField(value=12, name="carColor", requiredness=Requiredness.NONE)
    public String getCarColor() { return carColor; }

    @ThriftField
    public void setCarColor(final String carColor) { this.carColor = carColor; }

    private String useType;

    @ThriftField(value=13, name="useType", requiredness=Requiredness.NONE)
    public String getUseType() { return useType; }

    @ThriftField
    public void setUseType(final String useType) { this.useType = useType; }

    private String carStatus;

    @ThriftField(value=14, name="carStatus", requiredness=Requiredness.NONE)
    public String getCarStatus() { return carStatus; }

    @ThriftField
    public void setCarStatus(final String carStatus) { this.carStatus = carStatus; }

    private String registerDate;

    @ThriftField(value=15, name="registerDate", requiredness=Requiredness.NONE)
    public String getRegisterDate() { return registerDate; }

    @ThriftField
    public void setRegisterDate(final String registerDate) { this.registerDate = registerDate; }

    private String period;

    @ThriftField(value=16, name="period", requiredness=Requiredness.NONE)
    public String getPeriod() { return period; }

    @ThriftField
    public void setPeriod(final String period) { this.period = period; }

    private String mobile;

    @ThriftField(value=17, name="mobile", requiredness=Requiredness.NONE)
    public String getMobile() { return mobile; }

    @ThriftField
    public void setMobile(final String mobile) { this.mobile = mobile; }

    private String mobileHide;

    @ThriftField(value=18, name="mobileHide", requiredness=Requiredness.NONE)
    public String getMobileHide() { return mobileHide; }

    @ThriftField
    public void setMobileHide(final String mobileHide) { this.mobileHide = mobileHide; }

    private List<Business> business;

    @ThriftField(value=19, name="business", requiredness=Requiredness.NONE)
    public List<Business> getBusiness() { return business; }

    @ThriftField
    public void setBusiness(final List<Business> business) { this.business = business; }

    private List<VehicleFlow> vehicleflow;

    @ThriftField(value=20, name="vehicleflow", requiredness=Requiredness.NONE)
    public List<VehicleFlow> getVehicleflow() { return vehicleflow; }

    @ThriftField
    public void setVehicleflow(final List<VehicleFlow> vehicleflow) { this.vehicleflow = vehicleflow; }

    private int syq;

    @ThriftField(value=21, name="syq", requiredness=Requiredness.NONE)
    public int getSyq() { return syq; }

    @ThriftField
    public void setSyq(final int syq) { this.syq = syq; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("name", name)
            .add("nameHide", nameHide)
            .add("plateNo", plateNo)
            .add("vin", vin)
            .add("vinHide", vinHide)
            .add("engineNo", engineNo)
            .add("engineNoHide", engineNoHide)
            .add("plateType", plateType)
            .add("vehicleType", vehicleType)
            .add("brands", brands)
            .add("model", model)
            .add("carColor", carColor)
            .add("useType", useType)
            .add("carStatus", carStatus)
            .add("registerDate", registerDate)
            .add("period", period)
            .add("mobile", mobile)
            .add("mobileHide", mobileHide)
            .add("business", business)
            .add("vehicleflow", vehicleflow)
            .add("syq", syq)
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

        VehicleDetailDTO other = (VehicleDetailDTO)o;

        return
            Objects.equals(name, other.name) &&
            Objects.equals(nameHide, other.nameHide) &&
            Objects.equals(plateNo, other.plateNo) &&
            Objects.equals(vin, other.vin) &&
            Objects.equals(vinHide, other.vinHide) &&
            Objects.equals(engineNo, other.engineNo) &&
            Objects.equals(engineNoHide, other.engineNoHide) &&
            Objects.equals(plateType, other.plateType) &&
            Objects.equals(vehicleType, other.vehicleType) &&
            Objects.equals(brands, other.brands) &&
            Objects.equals(model, other.model) &&
            Objects.equals(carColor, other.carColor) &&
            Objects.equals(useType, other.useType) &&
            Objects.equals(carStatus, other.carStatus) &&
            Objects.equals(registerDate, other.registerDate) &&
            Objects.equals(period, other.period) &&
            Objects.equals(mobile, other.mobile) &&
            Objects.equals(mobileHide, other.mobileHide) &&
            Objects.equals(business, other.business) &&
            Objects.equals(vehicleflow, other.vehicleflow) &&
            Objects.equals(syq, other.syq);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            name,
            nameHide,
            plateNo,
            vin,
            vinHide,
            engineNo,
            engineNoHide,
            plateType,
            vehicleType,
            brands,
            model,
            carColor,
            useType,
            carStatus,
            registerDate,
            period,
            mobile,
            mobileHide,
            business,
            vehicleflow,
            syq
        });
    }
}