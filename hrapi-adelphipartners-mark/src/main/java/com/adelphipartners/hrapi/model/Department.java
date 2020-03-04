package com.adelphipartners.hrapi.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_UID")
	private long departmentUID;
	
	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "EMPLOYEES")
	@OneToMany(mappedBy = "department", cascade = CascadeType.MERGE)
	@JsonManagedReference
	@JsonProperty("Employees")
	@JsonFormat(shape=JsonFormat.Shape.OBJECT)
	private List<Employee> employees;
	
	
	//@OneToMany(mappedBy = "statementUID", cascade = CascadeType.MERGE)
	@ElementCollection
	@JsonProperty("Costs")
	@JsonFormat(shape=JsonFormat.Shape.OBJECT)
	private List<CostStatement> costs;
	
	
	@ElementCollection
	@JsonProperty("Revenues")
	@JsonFormat(shape=JsonFormat.Shape.OBJECT)
	private List<IncomeStatement> revenues;
	
	public Department() {}

	public Department(String name, List<CostStatement> costs, List<IncomeStatement> revenues)
	{
		this.departmentName = name;
		this.costs = costs;
		this.revenues = revenues;
		this.employees = new ArrayList<Employee>();
	}
	
	public Department(String name, List<CostStatement> costs, List<IncomeStatement> revenues, List<Employee> employees)
	{
		this.departmentName = name;
		this.costs = costs;
		this.revenues = revenues;
		this.employees = employees;
	}
	
	public long getDepartmentUID() {
		return departmentUID;
	}

	public void setDepartmentUID(long departmentUID) {
		this.departmentUID = departmentUID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<CostStatement> getCosts() {
		return costs;
	}

	public void setCosts(List<CostStatement> costs) {
		this.costs = costs;
	}

	public List<IncomeStatement> getRevenues() {
		return revenues;
	}

	public void setRevenues(List<IncomeStatement> revenues) {
		this.revenues = revenues;
	}
	
	public void addEmployee(Employee emp)
	{
		this.employees.add(emp);
	}
}
