package com.api.backend.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AccountDto {
    
    @NotBlank
    @Pattern(regexp="(\\w){4,}")
    private String username;

    @NotBlank
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
