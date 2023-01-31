package com.springboot.demo.model;

public class JournalEntryResponse {

    private boolean success;

    private String errorMessage;

    private Tasks data = new Tasks();

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Tasks getData() {
        return this.data;
    }

    public void setData(Tasks data) {
        this.data = data;
    }

}

