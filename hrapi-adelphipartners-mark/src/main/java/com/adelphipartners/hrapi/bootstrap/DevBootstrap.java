package com.adelphipartners.hrapi.bootstrap;

import com.adelphipartners.hrapi.model.CostStatement;
import com.adelphipartners.hrapi.model.Department;
import com.adelphipartners.hrapi.model.Employee;
import com.adelphipartners.hrapi.model.EmployeeStatus;
import com.adelphipartners.hrapi.model.IncomeStatement;
import com.adelphipartners.hrapi.model.OfficeLocation;
import com.adelphipartners.hrapi.model.Role;
import com.adelphipartners.hrapi.repository.DepartmentRepository;
import com.adelphipartners.hrapi.repository.EmployeeRepository;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final int DAYS_IN_A_YEAR = 365;
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private Faker faker = new Faker();

    
    public DevBootstrap(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) 
    {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) 
    {
    	populateTables();
    }

    private Employee createNewEmployee(Role role, int minAge, int maxAge,
                                       int maxTenureInYears, Employee manager) 
    {
        return new Employee(faker.name().firstName(),
                faker.name().firstName(), faker.name().lastName(),
                faker.date().birthday(minAge, maxAge).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), faker.address().country(), role, faker.date().past((maxTenureInYears * this.DAYS_IN_A_YEAR), TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), EmployeeStatus.ACTIVE, OfficeLocation.LON, (role == Role.CEO ? null : manager));
    }
    
    private Employee createNewEmployee(Role role, int minAge, int maxAge,
            int maxTenureInYears, Employee manager, Department dept) 
	{
    	EmployeeStatus status = EmployeeStatus.ACTIVE;
    	LocalDate termDate = null;
    	LocalDate hireDate = faker.date().past((maxTenureInYears * this.DAYS_IN_A_YEAR), TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	if(faker.random().nextDouble() <= 0.1)
    	{
    		status = EmployeeStatus.FIRED;
    		java.util.Date fakerDate = faker.date().between(Date.valueOf(hireDate), Date.valueOf(LocalDate.now()));
    		termDate = Instant.ofEpochMilli(fakerDate.getTime())
    			      .atZone(ZoneId.systemDefault())
    			      .toLocalDate();
    				
    	}
		return new Employee(faker.name().firstName(),
		faker.name().firstName(), faker.name().lastName(),
		faker.date().birthday(minAge, maxAge).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), faker.address().country(), role, hireDate, termDate, status, OfficeLocation.LON, (role == Role.CEO ? null : manager), dept);
	}
    /*
    private List<Employee> generateEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        Employee ceo = createNewEmployee(Role.CEO, 45, 50, 18, null);
        employeeList.add(ceo);

        List<Employee> departmentHeads = new ArrayList<>();
        Role[] departmentHeadsRoles = {Role.HUMAN_RESOURCES_VP,
                Role.ENGINEERING_VP, Role.SALES_VP, Role.SR_PARTNERSHIPS_VP,
                Role.MARKETING_VP};

        for (Role role : departmentHeadsRoles) {
            Employee departmentHead = createNewEmployee(role, 30, 50, 15, ceo);
            departmentHeads.add(departmentHead);
        }

        for (Employee departmentHead : departmentHeads) {
            employeeList.add(departmentHead);
            for (int i = 0; i < 30; i++) {
                Employee employee = createNewEmployee(Role.EMPLOYEE, 20, 40,
                        8, departmentHead);
                employeeList.add(employee);
            }
        }

        return employeeList;
    }

    private List<Department> genereateDepartments()
    {
    	List<Department> depts = new ArrayList<Department>();
    	
    	for(Role r : Role.values())
    	{
    		Department dept = new Department();
    		dept.setDepartmentName(r.label);
    		List<Employee> emps = new ArrayList<Employee>();    		
    		//dept.setEmployees(emps);
    		List<IncomeStatement> income = new LinkedList<>();
    		List<CostStatement> cost = new LinkedList<>();
    		LocalDate date = LocalDate.now();
    		for(int i = 0; i < 240; i++) 
    		{    			
    			IncomeStatement is = new IncomeStatement(date);  
    			CostStatement cs = new CostStatement(date);
    			income.add(is);
    			cost.add(cs);

    			date = date.minusMonths(1);
    		}
    		dept.setCosts(cost);
    		dept.setRevenues(income);
    	}
    	
    	return depts;
    }
    */
    public void populateTables() 
    {
    	Queue<Department> depts = new LinkedList<Department>();
    	List<Employee> employeeList = new ArrayList<>();
        List<Employee> departmentHeads = new ArrayList<>();
        Employee ceo = createNewEmployee(Role.CEO, 45, 50, 18, null);
        employeeList.add(ceo);
    	for(Role r : Role.values())
    	{  		
    		if(r.label != Role.EMPLOYEE.label)
    		{
    			List<IncomeStatement> income = new ArrayList<IncomeStatement>();
        		List<CostStatement> cost = new ArrayList<CostStatement>();
        		LocalDate date = LocalDate.now();
        		for(int i = 0; i < 240; i++) 
        		{    			
        			IncomeStatement is = new IncomeStatement(date);  
        			CostStatement cs = new CostStatement(date);
        			income.add(is);
        			cost.add(cs);
        			date = date.minus(Period.ofMonths(1));
        		}
        		
        		Department dept = new Department(r.label, cost, income);
        		
        		Employee departmentHead = createNewEmployee(r, 30, 50, 15, ceo,dept);
        		//dept.addEmployee(departmentHead);
        		departmentHeads.add(departmentHead); 
        		
    			employeeList.add(departmentHead);
                for (int i = 0; i < 30; i++) 
                {
                    Employee employee = createNewEmployee(Role.EMPLOYEE, 20, 40,
                            8, departmentHead,dept);
                    employeeList.add(employee);
                    //dept.addEmployee(employee);
                }
        		depts.add(dept);
    		}	
    	}
    	
    	for(Department dept : depts)
    	{
        	departmentRepository.save(dept);
	 	} 
    	for(Employee emp : employeeList)
    	{
    		employeeRepository.save(emp);
    	}
    	      
	}
}
