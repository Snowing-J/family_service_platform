package com.patrick.service.vo;

public class UnitMessage {

    private String buildingCode;
    private Integer unitCount;
    private String unitCode;

    public UnitMessage() {
    }

    public UnitMessage(String buildingCode, Integer unitCount) {
        this.buildingCode = buildingCode;
        this.unitCount = unitCount;
    }

    public UnitMessage(String buildingCode, String unitCode) {
        this.buildingCode = buildingCode;
        this.unitCode = unitCode;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public UnitMessage setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
        return this;
    }

    public Integer getUnitCount() {
        return unitCount;
    }

    public UnitMessage setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
        return this;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public UnitMessage setUnitCode(String unitCode) {
        this.unitCode = unitCode;
        return this;
    }

    @Override
    public String toString() {
        return "UnitMessage{" +
                "buildingCode='" + buildingCode + '\'' +
                ", unitCount=" + unitCount +
                ", unitCode='" + unitCode + '\'' +
                '}';
    }
}
