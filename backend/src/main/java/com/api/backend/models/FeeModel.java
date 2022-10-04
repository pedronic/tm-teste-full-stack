package com.api.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import java.util.Set;

@Entity
@Table(name = "TB_FEE")
public class FeeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "fee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID feeId;
    @Column(name="type", nullable = false)
    private String type;
    @Column(name="interval_min",nullable = false)
    private Integer intervalMin;
    @Column(name="interval_max",nullable = false)
    private Integer intervalMax;
    @Column(name="value_min",nullable = false)
    private Float valueMin;
    @Column(name="value_max",nullable = false)
    private Float valueMax;
    @Column(name="fee_value",nullable = false)
    private Float feeValue;
    @Column(name="fee_rate",nullable = false)
    private Float feeRate;
    
    @OneToMany(mappedBy = "fee")
    private Set<FeeModel> fee;
    
    
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
    
    public Float getValue_min() {
        return valueMin;
    }
    public void setValue_min(Float value_min) {
        this.valueMin = value_min;
    }
    
    public Float getValue_max() {
        return valueMax;
    }
    public void setValue_max(Float value_max) {
        this.valueMax = value_max;
    }
    
    public Float getFee_value() {
        return feeValue;
    }
    public void setFee_value(Float fee_value) {
        this.feeValue = fee_value;
    }
    
    public Float getFee_rate() {
        return feeRate;
    }
    public void setFee_rate(Float fee_rate) {
        this.feeRate = fee_rate;
    }
    
    public Set<FeeModel> getFee() {
        return fee;
    }
    public void setFee(Set<FeeModel> fee) {
        this.fee = fee;
    }
    
}
