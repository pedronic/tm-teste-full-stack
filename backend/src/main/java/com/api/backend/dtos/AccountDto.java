package com.api.backend.dtos;

import javax.validation.constraints.NotBlank;

public class AccountDto {

    @NotBlank
    private Float value;

    @NotBlank

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
    
}
