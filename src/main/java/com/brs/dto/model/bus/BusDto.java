package com.brs.dto.model.bus;

public class BusDto {

    private String code;
    private Integer capacity;
    private String make;

    public BusDto() {
    }

    public BusDto(String code, Integer capacity, String name) {
        this.code = code;
        this.capacity = capacity;
        this.make = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return "BusDto{" +
                "code='" + code + '\'' +
                ", capacity=" + capacity +
                ", make='" + make + '\'' +
                '}';
    }
}
