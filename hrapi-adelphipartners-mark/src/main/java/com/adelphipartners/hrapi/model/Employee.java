package com.adelphipartners.hrapi.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_UID")
    private long employeeUID;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DOB")
    private LocalDate dateOfBirth;
    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ROLE")
    private Role employeeRole;
    @Column(name = "DATE_HIRED")
    private LocalDate hireDate;
    @Column(name = "DATE_TERMINATED")
    private LocalDate termDate;
    @Column(name = "EMPLOYEE_STATUS")
    private EmployeeStatus employeeStatus;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "MANAGER", referencedColumnName = "EMPLOYEE_UID")
    @JsonBackReference
    private Employee manager;
    @Column(name = "EMPLOYEE_OFFICE")
    private OfficeLocation employeeOffice;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEES")
    @JsonBackReference
    private Department department;
    
    // To store personal identification documents for employees
    // private List<Image> identificationDocuments;

    public Employee() {
    }
    
    public Employee(String firstName, String middleName, String lastName,
                    LocalDate dateOfBirth, String country, Role employeeRole,
                    LocalDate hireDate, LocalDate termDate, EmployeeStatus employeeStatus,
                    OfficeLocation officeLocation, Employee manager, Department department) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.employeeRole = employeeRole;
        this.hireDate = hireDate;
        this.termDate = termDate;
        this.employeeStatus = employeeStatus;
        this.employeeOffice = officeLocation;
        this.manager = manager;
        this.department = department;
    }
    
    public Employee(String firstName, String middleName, String lastName,
            LocalDate dateOfBirth, String country, Role employeeRole,
            LocalDate hireDate, EmployeeStatus employeeStatus,
            OfficeLocation officeLocation, Employee manager) 
    {    
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
		this.employeeRole = employeeRole;
		this.hireDate = hireDate;
		this.employeeStatus = employeeStatus;
		this.employeeOffice = officeLocation;
		this.manager = manager;
    }

    public boolean isManager(long employeeUID) {
        Employee manager = this.getManager();
        return manager == null ? false :
                manager.getEmployeeUID() == employeeUID;
    }

	public long getEmployeeUID() {
		return employeeUID;
	}

	public void setEmployeeUID(long employeeUID) {
		this.employeeUID = employeeUID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Role getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(Role employeeRole) {
		this.employeeRole = employeeRole;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public OfficeLocation getEmployeeOffice() {
		return employeeOffice;
	}

	public void setEmployeeOffice(OfficeLocation employeeOffice) {
		this.employeeOffice = employeeOffice;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
