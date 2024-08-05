package ru.mts.client_data_validator.model;

import lombok.Getter;

@Getter
public class ValidationResult {
    private boolean isValid;
    private int fails = -1;
    private String failMessage;

    public ValidationResult(boolean isValid, int fails, String failMessage) {
        this.isValid = isValid;
        this.fails = fails;
        this.failMessage = failMessage;
    }

    public ValidationResult(boolean isValid) {
        this.isValid = isValid;
    }
}