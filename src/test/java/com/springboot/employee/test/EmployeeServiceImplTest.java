package com.springboot.employee.test;

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
		List<Employee> empList = employeeService.getEmployees();
		org.junit.Assert.assertNotNull(empList);
	}

	@Test
	public void test_validId_thenEmployeeShouldBeFound() {

		Employee emp = employeeService.getEmployee(new Long(1));
		org.junit.Assert.assertNotNull(emp);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void test_invalidId_thenNotFoundException() {
		Employee emp = employeeService.getEmployee(new Long(8));

	}

	@Test
	public void test_createEmployee_thenReturnEmployee() {

	}

	@Test
	public void test_updateValidId_thenEmployeeShouldBeReturned() {

		Employee firstEmp = new Employee("FName5", "LName1", 20, "Address1", 125, "abc@abc.com");

		employeeService.createEmployee(firstEmp);
		Employee alex = new Employee("FName6", "LName6", 20, "dept6", 125, "abc@abc.com");
		Employee emp = employeeService.updateEmployee(firstEmp.getId(), alex);
		org.junit.Assert.assertEquals(emp.getFirstName(), "FName6");
	}

	@Test(expected = ResourceNotFoundException.class)
	public void test_updateInvaliId_thenNotFoundException() {
		Employee alex = new Employee("FName6", "LName6", 20, "dept6", 125, "abc@abc.com");
		Employee emp = employeeService.updateEmployee(new Long(100), alex);

	}

	public void test_deleteValidId_thenEmployeeShouldBeReturned() {
		Employee emp = employeeService.deleteEmployee(new Long(1));
		try {
			employeeService.getEmployee(emp.getId());
			org.junit.Assert.assertTrue(false);
		} catch (ResourceNotFoundException ex) {
			org.junit.Assert.assertTrue(true);
		}

	}

	@Test(expected = ResourceNotFoundException.class)
	public void test_deleteInValidId_thenNotFoundException() {
		Employee emp = employeeService.deleteEmployee(50L);

	}
}
