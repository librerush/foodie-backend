package com.example.foodie.dto;

public class ResultDto {
    private boolean ok;

    private String description;

    public ResultDto() {
    }

    public ResultDto(boolean ok, String description) {
        this.ok = ok;
        this.description = description;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
