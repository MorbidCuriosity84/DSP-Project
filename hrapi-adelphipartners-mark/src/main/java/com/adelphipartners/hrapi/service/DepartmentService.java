package com.adelphipartners.hrapi.service;

import java.math.BigDecimal;
import java.util.List;

import com.adelphipartners.hrapi.model.CostStatement;
import com.adelphipartners.hrapi.model.Department;
import com.adelphipartners.hrapi.model.Employee;
import com.adelphipartners.hrapi.model.IncomeStatement;

public interface DepartmentService {
	List<Department> getAllDepts();

    Department getSpecificDepartment(long deptUID);

    //BigDecimal[] getStat(long deptUID, String data);

	BigDecimal[] getAttrition(long deptUID);
	
	List<CostStatement> getCosts(long deptUID);
	
	List<IncomeStatement> getRevenues(long deptUID);
	
	BigDecimal[] getTenure(long deptUID);
	
	BigDecimal[] getEmployeeCount(long deptUID);
	
	List<Employee> getEmployees(long deptUID);
}
