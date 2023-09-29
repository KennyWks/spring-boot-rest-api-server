package com.metrodata.serverapp.model.request;

public class RegionRequest {

    private String name;

    public RegionRequest() {
    }

    public RegionRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
