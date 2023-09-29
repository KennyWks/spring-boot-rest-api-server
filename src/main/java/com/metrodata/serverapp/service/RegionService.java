package com.metrodata.serverapp.service;

import com.metrodata.serverapp.model.request.RegionRequest;
import com.metrodata.serverapp.model.response.RegionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RegionService {
    List<RegionResponse> getAll();
    RegionResponse getById(long id);
    RegionResponse create(RegionRequest regionRequest);
    RegionResponse update(long id, RegionRequest regionRequest);
    RegionResponse delete(long id);
}
