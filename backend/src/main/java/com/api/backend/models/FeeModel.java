package com.api.backend.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.backend.dtos.FeeDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TB_FEE")
public class FeeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "fee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID feeId;
    @Column(name="type", nullable = false, unique = true)
    private String type;
    @Column(name="interval_min",nullable = false)
    private Integer intervalMin;
    @Column(name="interval_max",nullable = false)
    private Integer intervalMax;
    @Column(name="value_min",nullable = false)
    private BigDecimal valueMin;
    @Column(name="value_max",nullable = false)
    private BigDecimal valueMax;
    @Column(name="fee_value",nullable = false)
    private BigDecimal feeValue;
    @Column(name="fee_rate",nullable = false)
    private BigDecimal feeRate;
    
    @OneToMany(mappedBy = "fee")
    @JsonManagedReference
    private Set<FeeModel> fee;
    
    public FeeModel(){
        this.feeId = UUID.randomUUID();
    }

    public FeeModel(String type, Integer intervalMin, Integer intervalMax, BigDecimal valueMin, BigDecimal valueMax, BigDecimal feeValue, BigDecimal feeRate) {
        this.feeId = UUID.randomUUID();
        this.type = type;
        this.intervalMin = intervalMin;
        this.intervalMax = intervalMax;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.feeValue = feeValue;
        this.feeRate = feeRate;
    }

    @Autowired
    public FeeModel(FeeDto feeDto) {
        this.feeId = feeDto.getFeeId();
        this.type = feeDto.getType();
        this.intervalMin = feeDto.getIntervalMin();
        this.intervalMax = feeDto.getIntervalMax();
        this.valueMin = feeDto.getValueMin();
        this.valueMax = feeDto.getValueMax();
        this.feeValue = feeDto.getFeeValue();
        this.feeRate = feeDto.getFeeRate();
    }
    
    public UUID getFeeId() {
        return feeId;
    }
    public void setFeeId(UUID feeId) {
        this.feeId = feeId;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getInterval_min() {
        return intervalMin;
    }
    public void setInterval_min(Integer interval_min) {
        this.intervalMin = interval_min;
    }
    
    public Integer getInterval_max() {
        return intervalMax;
    }
    public void setInterval_max(Integer interval_max) {
        this.intervalMax = interval_max;
    }
    
    public BigDecimal getValue_min() {
        return valueMin;
    }
    public void setValue_min(BigDecimal value_min) {
        this.valueMin = value_min;
    }
    
    public BigDecimal getValue_max() {
        return valueMax;
    }
    public void setValue_max(BigDecimal value_max) {
        this.valueMax = value_max;
    }
    
    public BigDecimal getFee_value() {
        return feeValue;
    }
    public void setFee_value(BigDecimal fee_value) {
        this.feeValue = fee_value;
    }
    
    public BigDecimal getFee_rate() {
        return feeRate;
    }
    public void setFee_rate(BigDecimal fee_rate) {
        this.feeRate = fee_rate;
    }
    
    public Set<FeeModel> getFee() {
        return fee;
    }
    public void setFee(Set<FeeModel> fee) {
        this.fee = fee;
    }
    
    @Override
    public String toString() {
        return "{\n"+
        "\t feeId: "+ getFeeId()+",\n"+
        "\t type: "+ getType()+",\n"+
        "\t intervalMin: "+ getInterval_min()+",\n"+
        "\t intervalMax: "+ getInterval_max()+",\n"+
        "\t valueMin: "+ getValue_min().setScale(2, RoundingMode.UP).toPlainString()+",\n"+
        "\t valueMax: "+ getValue_max().setScale(2, RoundingMode.UP).toPlainString()+",\n"+
        "\t feeValue: "+ getFee_value().setScale(2, RoundingMode.UP).toPlainString()+",\n"+
        "\t feeRate: "+ getFee_rate().setScale(4, RoundingMode.UP).toPlainString()+",\n"+
        "}";
    }
}
