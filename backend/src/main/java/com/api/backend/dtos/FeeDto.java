package com.api.backend.dtos;

import java.math.BigDecimal;
import java.util.UUID;
import java.math.RoundingMode;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class FeeDto {
    
    public UUID feeId;
    @NotBlank
    public String type;
    @Min(value=-1)
    public Integer intervalMin;
    @Max(value=120)
    public Integer intervalMax;
    @Min(value=0)
    public BigDecimal valueMin;
    @Max(value=10000000)
    public BigDecimal valueMax;
    @Min(value=0)
    public BigDecimal feeValue;
    @Max(value=1)
    public BigDecimal feeRate;
    
    public FeeDto(){
        this.feeId = UUID.randomUUID();
    }
    
    public FeeDto(String type, Integer intervalMin, Integer intervalMax, Double valueMin, Double valueMax, Double feeValue, Double feeRate) {
        this.feeId = UUID.randomUUID();
        this.type = type;
        this.intervalMin = intervalMin;
        this.intervalMax = intervalMax;
        this.valueMin = new BigDecimal(valueMin);
        this.valueMin = this.valueMin.setScale(2, RoundingMode.UP);
        this.valueMax = new BigDecimal(valueMax);
        this.valueMax = this.valueMax.setScale(2, RoundingMode.UP);
        this.feeValue = new BigDecimal(feeValue);
        this.feeValue = this.feeValue.setScale(2, RoundingMode.UP);
        this.feeRate = new BigDecimal(feeRate);
        this.feeRate = this.feeRate.setScale(4, RoundingMode.UP);
    }
    
    public UUID getFeeId() {
        return feeId;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getIntervalMin() {
        return intervalMin;
    }
    public void setIntervalMin(Integer intervalMin) {
        this.intervalMin = intervalMin;
    }
    public Integer getIntervalMax() {
        return intervalMax;
    }
    public void setIntervalMax(Integer intervalMax) {
        this.intervalMax = intervalMax;
    }
    public BigDecimal getValueMin() {
        return valueMin;
    }
    public void setValueMin(BigDecimal valueMin) {
        this.valueMin = valueMin;
    }
    public BigDecimal getValueMax() {
        return valueMax;
    }
    public void setValueMax(BigDecimal valueMax) {
        this.valueMax = valueMax;
    }
    public BigDecimal getFeeValue() {
        return feeValue;
    }
    public void setFeeValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }
    public BigDecimal getFeeRate() {
        return feeRate;
    }
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }


}
