package com.springboot.demo.model;

public class JournalEntryResponse {

    private boolean success;

    private String errorMessage;

    private JournalEntry data = new JournalEntry();

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

    public JournalEntry getData() {
        return this.data;
    }

    public void setData(JournalEntry data) {
        this.data = data;
    }

}

