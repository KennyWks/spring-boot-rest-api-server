package com.metrodata.serverapp.service;

import com.metrodata.serverapp.model.request.EmployeeRequest;
import com.metrodata.serverapp.model.request.RegistrationRequest;
import com.metrodata.serverapp.model.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAll();
    EmployeeResponse getById(long id);
    EmployeeResponse create(RegistrationRequest registrationRequest);
    EmployeeResponse update(long id, EmployeeRequest employeeRequest);
    EmployeeResponse delete(long id);

}
