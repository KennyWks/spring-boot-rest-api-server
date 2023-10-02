package com.metrodata.serverapp.model.response;

public class CountryResponse {
    private long id;
    private String code;
    private String name;
    private long regionId;

    public CountryResponse() {
    }

    public CountryResponse(long id, String code, String name, long regionId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.regionId = regionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }
}
