package com.adelphipartners.hrapi.service;

import com.adelphipartners.hrapi.model.Employee;
import com.adelphipartners.hrapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Employee getSpecificEmployee(long employeeUID) {
        return employeeRepository.findById(employeeUID).get();
    }

    @Override
    public List<Employee> getSubordinates(long employeeUID) {
        return getAllEmployees().stream().filter(employee -> employee.isManager(employeeUID)).collect(Collectors.toList());
    }
}
