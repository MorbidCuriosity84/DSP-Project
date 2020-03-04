package com.adelphipartners.hrapi.repository;

import com.adelphipartners.hrapi.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
