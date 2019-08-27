package com.brs.dto.model.bus;

public class StopDto implements Comparable {

    private String code;
    private String name;
    private String detail;

    public StopDto() {
    }

    public StopDto(String code, String name, String detail) {
        this.code = code;
        this.name = name;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "StopDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((StopDto) o).getName());
    }
}
