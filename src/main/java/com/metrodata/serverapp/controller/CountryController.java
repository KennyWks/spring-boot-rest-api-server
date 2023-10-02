package com.metrodata.serverapp.controller;

import com.metrodata.serverapp.model.request.CountryRequest;
import com.metrodata.serverapp.model.response.CountryResponse;
import com.metrodata.serverapp.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

//    @Qualifier("CountryServiceImplV2")
    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryResponse> getAll(){
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public CountryResponse getById(@PathVariable long id){
        return countryService.getById(id);
    }

    @PostMapping
    public CountryResponse create(@RequestBody CountryRequest countryRequest){
        return countryService.create(countryRequest);
    }

    @PutMapping("/{id}")
    public CountryResponse update(@PathVariable long id, @RequestBody CountryRequest countryRequest){
        return countryService.update(id,countryRequest);
    }

    @DeleteMapping("/{id}")
    public CountryResponse delete(@PathVariable long id){
        return countryService.delete(id);
    }

}
