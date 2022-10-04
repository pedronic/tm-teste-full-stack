package com.api.backend.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.checkerframework.checker.index.qual.Positive;
import org.springframework.format.annotation.DateTimeFormat;

public class TransactionDto {

    @NotBlank
    private Integer accountFrom;
    @NotBlank
    private Integer accountTo;
    @NotBlank
    @Positive
    private Float value;
    @NotBlank
    @DateTimeFormat
    private LocalDateTime dateScheduled;

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public LocalDateTime getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(LocalDateTime dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

}
