package com.springboot.demo.model;

import java.util.ArrayList;
import java.util.List;

public class GetMonthEntryResponse {

    private boolean success;

    private String errorMessage;

    private List<Tasks> monthdata = new ArrayList<Tasks>(

    );

    public List<Tasks> getMonthData() {
        return monthdata;
    }

    public void setmonthdata(List<Tasks> monthdata) {
        this.monthdata = monthdata;
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
