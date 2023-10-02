package com.metrodata.serverapp.controller;

import com.metrodata.serverapp.model.request.CountryRequest;
import com.metrodata.serverapp.model.response.CountryResponse;
import com.metrodata.serverapp.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/country")
public class CountryController {

//    @Qualifier("CountryServiceImplV2")
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAll(){
        return new ResponseEntity<>(countryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getById(@PathVariable long id){
        return new ResponseEntity(countryService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CountryResponse> create(@RequestBody CountryRequest countryRequest){
        return new ResponseEntity<>(countryService.create(countryRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> update(@PathVariable long id, @RequestBody CountryRequest countryRequest){
        return new ResponseEntity<>( countryService.update(id,countryRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CountryResponse> delete(@PathVariable long id){
        return new ResponseEntity<>(countryService.delete(id),HttpStatus.OK);
    }

}
