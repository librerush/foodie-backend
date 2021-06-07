package com.example.foodie.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultDto resultDto = (ResultDto) o;
        return ok == resultDto.ok && Objects.equals(description, resultDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ok, description);
    }
}
