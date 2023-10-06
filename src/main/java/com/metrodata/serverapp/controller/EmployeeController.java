package com.metrodata.serverapp.controller;

import com.metrodata.serverapp.model.request.EmployeeRequest;
import com.metrodata.serverapp.model.request.RegistrationRequest;
import com.metrodata.serverapp.model.response.EmployeeResponse;
import com.metrodata.serverapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable long id){
        return new ResponseEntity<>(employeeService.getById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(employeeService.create(registrationRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    public ResponseEntity<EmployeeResponse> update(@PathVariable long id, @RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.update(id,employeeRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    public ResponseEntity<EmployeeResponse> delete(@PathVariable long id){
        return new ResponseEntity<>(employeeService.delete(id),HttpStatus.OK);
    }
}
