package com.springboot.employee.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.employee.SpringBootEmployeeApplication;
import com.springboot.employee.exception.ResourceNotFoundException;
import com.springboot.employee.model.Employee;
import com.springboot.employee.service.EmployeeService;
import com.springboot.employee.service.impl.EmployeeServiceImpl;

/**
 * Test Class for Employee Service
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootEmployeeApplication.class)
public class EmployeeServiceImplTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	private EmployeeService employeeService;

	@Before
	public void setUp() {
	}

	@Test
	public void test_findAll_employees() {
		Employee firstEmp = new Employee("FName1", "LName1", 20, "Address1", 125, "abc@abc.com");
		employeeService.createEmployee(firstEmp);
		Employee secEmp = new Employee("FName2", "LName2", 20, "Address2", 125, "abc2@abc.com");
		employeeService.createEmployee(secEmp);
		List<Employee> empList = employeeService.getEmployees();
		assertNotNull(empList);
		assertEquals(empList.size(),2);
	}

	@Test
	public void test_validId_thenEmployeeShouldBeFound() {

		Employee firstEmp = new Employee("FName1", "LName1", 20, "Address1", 125, "abc@abc.com");
		employeeService.createEmployee(firstEmp);
		Employee emp = employeeService.getEmployee(new Long(1));
		assertNotNull(emp);
		assertEquals(emp.getFirstName(),"FName1");
	}

	@Test(expected = ResourceNotFoundException.class)
	public void test_invalidId_thenNotFoundException() {
		Employee emp = employeeService.getEmployee(new Long(8));

	}

	@Test
	public void test_createEmployee_thenEmployeeShouldBeReturned() {
		Employee firstEmp = new Employee("FName5", "LName1", 20, "Address1", 125, "abc@abc.com");
		Employee emp = employeeService.createEmployee(firstEmp);
		assertNotNull(emp);
		assertEquals(emp.getFirstName(), "FName5");
	}

	@Test
	public void test_updateValidId_thenEmployeeShouldBeReturned() {

		Employee firstEmp = new Employee("FName5", "LName1", 20, "Address1", 125, "abc@abc.com");

		employeeService.createEmployee(firstEmp);
		Employee emp = new Employee("FName6", "LName6", 20, "dept6", 125, "abc@abc.com");
		Employee updEmp = employeeService.updateEmployee(firstEmp.getId(), emp);
		assertEquals(updEmp.getFirstName(), "FName6");
	}

	@Test(expected = ResourceNotFoundException.class)
	public void test_updateInvaliId_thenNotFoundException() {
		Employee emp = new Employee("FName6", "LName6", 20, "dept6", 125, "abc@abc.com");
		Employee newEmp = employeeService.updateEmployee(new Long(100), emp);

	}

	public void test_deleteValidId_thenEmployeeShouldBeReturned() {
		Employee emp = employeeService.deleteEmployee(new Long(1));
		try {
			employeeService.getEmployee(emp.getId());
			assertTrue(false);
		} catch (ResourceNotFoundException ex) {
			assertTrue(true);
		}

	}

	@Test(expected = ResourceNotFoundException.class)
	public void test_deleteInValidId_thenNotFoundException() {
		Employee emp = employeeService.deleteEmployee(50L);

	}
}
