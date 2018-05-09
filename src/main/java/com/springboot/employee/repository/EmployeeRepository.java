package com.springboot.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.employee.model.Employee;

/**
 * Employee Repository Class - extends from spring data JPA Repository
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
