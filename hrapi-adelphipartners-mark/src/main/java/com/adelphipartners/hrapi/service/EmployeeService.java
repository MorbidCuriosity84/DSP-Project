package com.adelphipartners.hrapi.service;

import com.adelphipartners.hrapi.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getSpecificEmployee(long employeeUID);

    List<Employee> getSubordinates(long employeeUID);
}
