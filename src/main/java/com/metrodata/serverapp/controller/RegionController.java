package com.metrodata.serverapp.controller;

import com.metrodata.serverapp.model.request.RegionRequest;
import com.metrodata.serverapp.model.response.RegionResponse;
import com.metrodata.serverapp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller // HTML, @ResponseBody JSON
@RestController // API => JSON
@RequestMapping("/region")
public class RegionController {

    private RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    // http://localhost:8088/region
    @GetMapping
    List<RegionResponse> getAll() {
        return regionService.getAll();
    }

    // http://localhost:8088/region?id=1 => Path Parameter (Achmad, Edwin, Nur, Vio )
    // http://localhost:8088/region/1 => Path Variable (Arif, Kenny, Rutri, Valery )
    @GetMapping("/{id}")
    RegionResponse getById(@PathVariable long id) {
        return regionService.getById(id);
    }

    // http://localhost:8088/region/create
    // http://localhost:8088/region (Arif)
    @PostMapping
    RegionResponse create(@RequestBody RegionRequest regionRequest){
        return regionService.create(regionRequest);
    }

    // http://localhost:8088/region/1
    @PutMapping("/{id}")
    RegionResponse update(@PathVariable long id, @RequestBody RegionRequest regionRequest){
        return regionService.update(id,regionRequest);
    }

    // http://localhost:8088/region/1
    @DeleteMapping("/{id}")
    RegionResponse delete(@PathVariable long id){
        return regionService.delete(id);
    }

}
