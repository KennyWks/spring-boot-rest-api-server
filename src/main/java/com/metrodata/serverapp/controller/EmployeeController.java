package com.metrodata.serverapp.controller;

import com.metrodata.serverapp.model.request.RegistrationRequest;
import com.metrodata.serverapp.model.response.EmployeeResponse;
import com.metrodata.serverapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable long id){
        return new ResponseEntity<>(employeeService.getById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(employeeService.create(registrationRequest), HttpStatus.CREATED);
    }

}
