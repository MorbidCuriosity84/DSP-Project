package com.adelphipartners.hrapi.controller;

import com.adelphipartners.hrapi.model.Employee;
import com.adelphipartners.hrapi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees",  produces = MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/employees/{employeeUID}",  produces = MediaType.APPLICATION_JSON)
    public Employee getSpecificEmployee(@PathVariable long employeeUID) {
        return employeeService.getSpecificEmployee(employeeUID);
    }

    @GetMapping(value = "/subordinates/{employeeUID}",  produces = MediaType.APPLICATION_JSON)
    public List<Employee> getSubordinates(@PathVariable long employeeUID) {
        return employeeService.getSubordinates(employeeUID);
    }

}
