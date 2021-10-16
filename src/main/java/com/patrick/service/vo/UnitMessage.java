package com.patrick.service.vo;

public class UnitMessage {

    private String buildingCode;
    private Integer unitCount;

    public UnitMessage() {
    }

    public UnitMessage(String buildingCode, Integer unitCount) {
        this.buildingCode = buildingCode;
        this.unitCount = unitCount;
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

    @Override
    public String toString() {
        return "UnitMassage{" +
                "buildingCode='" + buildingCode + '\'' +
                ", unitCount=" + unitCount +
                '}';
    }
}
