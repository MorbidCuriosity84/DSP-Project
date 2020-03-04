package com.adelphipartners.hrapi.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adelphipartners.hrapi.model.CostStatement;
import com.adelphipartners.hrapi.model.Department;
import com.adelphipartners.hrapi.model.Employee;
import com.adelphipartners.hrapi.model.EmployeeStatus;
import com.adelphipartners.hrapi.model.IncomeStatement;
import com.adelphipartners.hrapi.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
    private DepartmentRepository deptRepository;
	
	@Override
	public List<Department> getAllDepts() {
		return StreamSupport.stream(deptRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public Department getSpecificDepartment(long deptUID) {
		return deptRepository.findById(deptUID).get();
	}
	
	public BigDecimal daysBetween(LocalDate dateFrom, LocalDate dateTo)
	{		
		long daysBetween = ChronoUnit.DAYS.between(dateFrom, dateTo);
		return BigDecimal.valueOf(daysBetween);
	}

	@Override
	public BigDecimal[] getAttrition(long deptUID) {
		BigDecimal bdAttRate = BigDecimal.ZERO;
		List<Employee> listEmployees = getEmployees(deptUID);
		
		for(Employee emp : listEmployees)
		{
			if(emp.getDepartment() != null) 
			{
				if(		emp.getDepartment().getDepartmentUID() == deptUID && 
						emp.getEmployeeStatus() == EmployeeStatus.FIRED )
				{
					bdAttRate = bdAttRate.add(BigDecimal.ONE);
				}
			}
		}
		BigDecimal[] empCount = getEmployeeCount(deptUID);
		BigDecimal[] retVal = new BigDecimal[1];
		retVal[0] = bdAttRate.divide(empCount[0], 2, RoundingMode.HALF_UP);
		return retVal;
	}

	@Override
	public List<CostStatement> getCosts(long deptUID) {
		return deptRepository.findById(deptUID).get().getCosts();
	}

	@Override
	public List<IncomeStatement> getRevenues(long deptUID) {
		return deptRepository.findById(deptUID).get().getRevenues();
	}

	@Override
	public BigDecimal[] getTenure(long deptUID) {
		BigDecimal[] retVal = new BigDecimal[1];
		BigDecimal bdTenure = BigDecimal.ZERO;
		List<Employee> listEmployees = getEmployees(deptUID);
		
		LocalDate now = LocalDate.now();
		for(Employee emp : listEmployees)
		{
			if(emp.getDepartment() != null) 
			{
				if(		emp.getDepartment().getDepartmentUID() == deptUID && 
					emp.getEmployeeStatus() == EmployeeStatus.ACTIVE )
				{
					bdTenure = bdTenure.add(daysBetween(emp.getHireDate(), now));
				}
			}
		}
		BigDecimal employeeCount = getEmployeeCount(deptUID)[0];
		retVal[0] =  bdTenure.divide(employeeCount, 0, RoundingMode.HALF_UP);
		return retVal;
	}

	@Override
	public BigDecimal[] getEmployeeCount(long deptUID) {
		BigDecimal[] retVal;
		BigDecimal bdCount = BigDecimal.ZERO;
		List<Employee> listEmployees = getEmployees(deptUID);
		bdCount = BigDecimal.valueOf(listEmployees.size());
		retVal = new BigDecimal[1];
		retVal[0] = bdCount;
		return retVal;	
	}

	@Override
	public List<Employee> getEmployees(long deptUID) {
		return StreamSupport.stream(deptRepository.findById(deptUID).get().getEmployees().spliterator(), false).collect(Collectors.toList());
	}

}
