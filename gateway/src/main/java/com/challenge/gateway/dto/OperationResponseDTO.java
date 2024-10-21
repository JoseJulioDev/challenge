package com.challenge.gateway.dto;

import java.math.BigDecimal;

public class OperationResponseDTO {

    private BigDecimal result;
    private String error;

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
