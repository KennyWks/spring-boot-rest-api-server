package com.metrodata.serverapp.service.impl;

import com.metrodata.serverapp.entity.Region;
import com.metrodata.serverapp.exception.CustomException;
import com.metrodata.serverapp.model.request.RegionRequest;
import com.metrodata.serverapp.model.response.RegionResponse;
import com.metrodata.serverapp.repository.RegionRepository;
import com.metrodata.serverapp.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegionServiceImpl implements RegionService {
    private  RegionRepository regionRepository;

    @Override
    public List<RegionResponse> getAll() {
        return regionRepository.findAll()
                .stream()
                .map(region -> {
                    return mappingRegionToRegionResponse(region);
                })
                .collect(Collectors.toList());// List<RegionResponse>
    }

    @Override
    public RegionResponse getById(long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "Region with given id " + id + " not found",
                        "REGION_NOT_FOUND",
                        404));
        return mappingRegionToRegionResponse(region);
    }

    @Override
    public RegionResponse create(RegionRequest regionRequest) {
        Region region = new Region();
        region.setName(regionRequest.getName());
        Region res = regionRepository.save(region);
        return mappingRegionToRegionResponse(res);
    }

    @Override
    public RegionResponse update(long id, RegionRequest regionRequest) {
        RegionResponse oldData = getById(id);

        Region region = new Region();
        region.setId(oldData.getId());
        region.setName(oldData.getName());
        region.setName(regionRequest.getName());

        Region res = regionRepository.save(region);
        return mappingRegionToRegionResponse(res);
    }

    @Override
    public RegionResponse delete(long id) {
        RegionResponse regionResponse = getById(id);
        regionRepository.deleteById(id);
        return regionResponse;
    }


    public RegionResponse mappingRegionToRegionResponse(Region region) {
        RegionResponse regionResponse = new RegionResponse();
        regionResponse.setId(region.getId());
        regionResponse.setName(region.getName());
        return regionResponse;
    }
}
