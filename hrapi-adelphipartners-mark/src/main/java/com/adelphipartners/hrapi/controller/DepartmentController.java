package com.adelphipartners.hrapi.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adelphipartners.hrapi.model.CostStatement;
import com.adelphipartners.hrapi.model.Department;
import com.adelphipartners.hrapi.model.Employee;
import com.adelphipartners.hrapi.model.IncomeStatement;
import com.adelphipartners.hrapi.service.DepartmentServiceImpl;
import com.adelphipartners.hrapi.service.EmployeeServiceImpl;

@RestController
public class DepartmentController {

    @Autowired
    private final DepartmentServiceImpl deptService;

    public DepartmentController(DepartmentServiceImpl deptService) {
        this.deptService = deptService;
    }

    @GetMapping(value = "/departments",  produces = MediaType.APPLICATION_JSON)
    public List<Department> getAllDepts() {
        return deptService.getAllDepts();
    }

    @GetMapping(value = "/departments/{deptUID}",  produces = MediaType.APPLICATION_JSON)
    public Department getSpecificEmployee(@PathVariable long deptUID) {
        return deptService.getSpecificDepartment(deptUID);
    }

    @GetMapping(value = "/departments/{deptUID}/attrition", produces = MediaType.APPLICATION_JSON)
    public BigDecimal[] getAttritionRate(@PathVariable long deptUID)
    {
    	return deptService.getAttrition(deptUID);
    }
    
    @GetMapping(value = "/departments/{deptUID}/employeesCount", produces = MediaType.APPLICATION_JSON)
    public BigDecimal[] getEmployeeCount(@PathVariable long deptUID)
    {
    	return deptService.getEmployeeCount(deptUID);
    }
    
    @GetMapping(value = "/departments/{deptUID}/employees", produces = MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees(@PathVariable long deptUID)
    {
    	return deptService.getEmployees(deptUID);
    }
    
    @GetMapping(value = "/departments/{deptUID}/cost", produces = MediaType.APPLICATION_JSON)
    public List<CostStatement> getCost(@PathVariable long deptUID)
    {
    	return deptService.getCosts(deptUID);
    }
    
    @GetMapping(value = "/departments/{deptUID}/revenue", produces = MediaType.APPLICATION_JSON)
    public List<IncomeStatement> getRevenues(@PathVariable long deptUID)
    {
    	return deptService.getRevenues(deptUID);
    }

    @GetMapping(value = "/departments/{deptUID}/tenure", produces = MediaType.APPLICATION_JSON)
    public BigDecimal[] getTenure(@PathVariable long deptUID)
    {
    	return deptService.getTenure(deptUID);
    }
}