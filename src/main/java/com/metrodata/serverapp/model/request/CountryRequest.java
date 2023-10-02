package com.metrodata.serverapp.model.request;

import javax.persistence.Column;

public class CountryRequest {
    private String code;
    private String name;
    private long regionId;

    public CountryRequest() {
    }

    public CountryRequest(String code, String name, long regionId) {
        this.code = code;
        this.name = name;
        this.regionId = regionId;
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
