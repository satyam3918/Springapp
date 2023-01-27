package com.springboot.demo.model;

import java.util.ArrayList;
import java.util.List;

public class GetCurrentMonthEntryResponse {

    private boolean success;

    private String errorMessage;

    private List<JournalEntry> currentmonthdata = new ArrayList<JournalEntry>();

    public List<JournalEntry> getCurrentmonthdata() {
        return currentmonthdata;
    }

    public void setCurrentmonthdata(List<JournalEntry> currentmonthdata) {
        this.currentmonthdata = currentmonthdata;
    }

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

}
