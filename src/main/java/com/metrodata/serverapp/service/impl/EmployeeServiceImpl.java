package com.metrodata.serverapp.service.impl;


import com.metrodata.serverapp.entity.Employee;
import com.metrodata.serverapp.entity.Role;
import com.metrodata.serverapp.entity.User;
import com.metrodata.serverapp.exception.CustomException;
import com.metrodata.serverapp.model.request.EmployeeRequest;
import com.metrodata.serverapp.model.request.RegistrationRequest;
import com.metrodata.serverapp.model.response.EmployeeResponse;
import com.metrodata.serverapp.model.response.UserResponse;
import com.metrodata.serverapp.repository.EmployeeRepository;
import com.metrodata.serverapp.repository.RoleRepository;
import com.metrodata.serverapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> {
                    return mappingEmployeeToEmployeeResponse(employee);
                }).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "Employee with given id : " + id + " not found",
                        "EMPLOYEE_NOT_FOUND",
                        404
                ));
        return mappingEmployeeToEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse create(RegistrationRequest registrationRequest) {

        // Mapping EmployeeRequest -> Employee
        Employee employee = new Employee();
        BeanUtils.copyProperties(registrationRequest,employee);

        // Mapping EmployeeRequest -> User
        User user = new User();
        BeanUtils.copyProperties(registrationRequest, user);
        user.setEmployee(employee);

        // Add Role on user
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findById(1L).get(); // User Role
        roles.add(role);
        user.setRoles(roles);

        // Set User on Employee
        employee.setUser(user);

        employeeRepository.save(employee);

        return mappingEmployeeToEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse update(long id, EmployeeRequest employeeRequest) {
        return null;
    }

    @Override
    public EmployeeResponse delete(long id) {
        return null;
    }


    public EmployeeResponse mappingEmployeeToEmployeeResponse(Employee employee){
        // Mapping Employee -> EmployeeResponse
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);

        // Mapping User -> UserResponse
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(employee.getUser(),userResponse);
        employeeResponse.setUser(userResponse);

        return employeeResponse;
    }
}
