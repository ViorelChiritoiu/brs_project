package com.brs.dto.model.bus;

import com.brs.dto.model.user.UserDto;

import java.util.List;

public class AgencyDto {

    private String code;
    private UserDto owner;
    private List<BusDto> buses;
    private String name;
    private String details;

    public AgencyDto() {
    }

    public AgencyDto(String code, UserDto owner, List<BusDto> buses, String name, String details) {
        this.code = code;
        this.owner = owner;
        this.buses = buses;
        this.name = name;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public List<BusDto> getBuses() {
        return buses;
    }

    public void setBuses(List<BusDto> buses) {
        this.buses = buses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "AgencyDto{" +
                "code='" + code + '\'' +
                ", owner=" + owner +
                ", buses=" + buses +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
