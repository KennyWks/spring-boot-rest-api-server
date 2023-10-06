package com.metrodata.serverapp.controller;

import com.metrodata.serverapp.model.request.RegionRequest;
import com.metrodata.serverapp.model.response.RegionResponse;
import com.metrodata.serverapp.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller // HTML, @ResponseBody JSON
@RestController // API => JSON
@RequestMapping("/region")
@AllArgsConstructor
@PreAuthorize("hasRole('USER')")
public class RegionController {

    private RegionService regionService;


    // http://localhost:8088/region
    @GetMapping
    @PreAuthorize("hasAuthority('READ_USER')")
    ResponseEntity<List<RegionResponse>> getAll() {
        return new ResponseEntity<>(regionService.getAll(), HttpStatus.OK);
    }

    // http://localhost:8088/region?id=1 => Path Parameter (Achmad, Edwin, Nur, Vio )
    // http://localhost:8088/region/1 => Path Variable (Arif, Kenny, Rutri, Valery )
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_USER')")
    ResponseEntity<RegionResponse> getById(@PathVariable long id) {
        return new ResponseEntity<>(regionService.getById(id), HttpStatus.OK);
    }

    // http://localhost:8088/region/create
    // http://localhost:8088/region (Arif)
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_USER')")
    ResponseEntity<RegionResponse> create(@RequestBody RegionRequest regionRequest){
        return new ResponseEntity<>(regionService.create(regionRequest),HttpStatus.CREATED);
    }

    // http://localhost:8088/region/1
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    ResponseEntity<RegionResponse> update(@PathVariable long id, @RequestBody RegionRequest regionRequest){
        return new ResponseEntity<>(regionService.update(id,regionRequest),HttpStatus.OK);
    }

    // http://localhost:8088/region/1
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    ResponseEntity<RegionResponse> delete(@PathVariable long id){
        return new ResponseEntity<>(regionService.delete(id), HttpStatus.OK) ;
    }

}
