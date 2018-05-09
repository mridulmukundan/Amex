package com.springboot.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.model.Employee;
import com.springboot.employee.service.impl.EmployeeServiceImpl;


@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl employeeService;
		
	
	/**
	 * Get All Employees
	 * @param None
	 * @return List<Employee>
	 */
	@RequestMapping(value="/employees/", method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees()
	{
		List<Employee> empList = employeeService.getEmployees();
		return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
	}
	
	
	/**
	 * Get employee by Id
	 * @param id
	 * @return ResponseEntity<Employee>
	 */
	@RequestMapping(value="/employees/{id}", method=RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable long id)
	{
		Employee employee = employeeService.getEmployee(id);
		if(employee == null)
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	}
	
	/**
	 * Create Employee
	 * @param employee
	 * @return ResponseEntity<Employee>
	 */
	@RequestMapping(value="/employees/", method=RequestMethod.POST)
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee)
	{
		Employee emp = employeeService.createEmployee(employee);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	
	
	/**
	 * Update Employee by Id
	 * @param id
	 * @param employee
	 * @return ResponseEntity<Employee>
	 */
	@RequestMapping(value="/employees/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee)
	{
		Employee emp = employeeService.updateEmployee(id,employee);
		if(emp == null)
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);	
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	/**
	 * Delete Employee by Id
	 * @param id
	 * @return ResponseEntity<Employee>
	 */
	@RequestMapping(value="/employees/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id)
	{
		Employee emp = employeeService.deleteEmployee(id);
		if(emp == null)
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}

}
