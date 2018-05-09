package com.springboot.employee.service;

import java.util.List;

import com.springboot.employee.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployees();
	
	public Employee getEmployee(Long id);
	
	
	public Employee createEmployee(Employee emp);
	
	
	public Employee updateEmployee(Long id, Employee emp);
	
	
	public Employee deleteEmployee(Long id);
	public void deleteAll();

}
