package com.springboot.employee.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.employee.exception.ResourceNotFoundException;
import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.EmployeeRepository;
import com.springboot.employee.service.EmployeeService;


/**
 * Employee Service Implementation Class
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	
	@Autowired
	EmployeeRepository empRepo;
	
	/*
	 * return all employess
	 */
	public List<Employee> getEmployees()
	{
		return empRepo.findAll();
	}
	
	/*
	 * return an employee By Id
	 * throws ResourceNotFoundException if employee by given ID not present
	 */
	public Employee getEmployee(Long id)
	{
		return empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		
	}
	
	/*
	 * create an employee
	 */
	public Employee createEmployee(Employee emp)
	{
		return empRepo.save(emp);
	}
	
	/*
	 * update employee by ID
	 * throws ResourceNotFoundException if employee by given ID not present
	 */
	public Employee updateEmployee(Long id, Employee emp)
	{
		Optional<Employee> optEmployee = empRepo.findById(id);
		if(!optEmployee.isPresent())
			throw new ResourceNotFoundException("Employee", "id", id);
		Employee employee = optEmployee.get();
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setAge(emp.getAge());
		employee.setAddress(emp.getAddress());		
		employee.setPhoneNo(emp.getPhoneNo());
		employee.setEmail(emp.getEmail());
		
		Employee updatedEmp = empRepo.save(employee);
		return updatedEmp;
	}
	
	/*
	 * delete an employee By Id
	 * throws ResourceNotFoundException if employee by given ID not present
	 */
	public Employee deleteEmployee(Long id)
	{
		/*Optional<Employee> optEmployee = empRepo.findById(id);
		if(!optEmployee.isPresent())
			throw new ResourceNotFoundException("Employee", "id", id);*/
		Employee emp = empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		empRepo.delete(emp);
		return emp;
	}
	
	/*
	 * Delete All employees
	 */
	public void deleteAll()
	{
		empRepo.deleteAll();;
	}

}
