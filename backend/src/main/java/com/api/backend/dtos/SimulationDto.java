package com.api.backend.dtos;

import java.math.BigDecimal;

public class SimulationDto {
    private BigDecimal fee_rate;   
    private BigDecimal fee_total;
    private BigDecimal fee_value;    
    private BigDecimal subtotal;    
    private String type;

    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    public BigDecimal getFee_value() {
        return fee_value;
    }
    public void setFee_value(BigDecimal fee_value) {
        this.fee_value = fee_value;
    }
    
    public BigDecimal getFee_rate() {
        return fee_rate;
    }
    public void setFee_rate(BigDecimal fee_rate) {
        this.fee_rate = fee_rate;
    }
    
    public BigDecimal getFee_total() {
        return fee_total;
    }
    public void setFee_total(BigDecimal fee_total) {
        this.fee_total = fee_total;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{\n" +
        "\t \"fee_rate\": "+ getFee_rate().toPlainString()+",\n"+
        "\t \"fee_total\": "+ getFee_total().toPlainString()+",\n"+
        "\t \"fee_value\": "+ getFee_value().toPlainString()+",\n"+
        "\t \"subtotal\": "+ getSubtotal().toPlainString()+",\n"+
        "\t \"type\": \""+ getType()+"\"\n"+
        "}";
    }
}
