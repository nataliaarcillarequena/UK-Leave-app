package com.user.main;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.user.entity.Employee;
import com.user.service.UserServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class LeaveMvc1ApplicationTests {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	/*
	 * to pass tests- make sure to start from an empty employee table 
	 */

	//successful
	@Test
	@Order(4)
	void authEmpCheckTest() {
		assertEquals("Authenticated User", userServiceImpl.authEmpCheck(178, "yrs").getAuthStatus());
	}
	
	//successful
	@Test
	@Order(5)
	void authEmpCheckTestNeg() {
		assertEquals("Incorrect Password", userServiceImpl.authEmpCheck(178, "xyz"));
	}

	//successful
	@Test
	@Order(6)
	void authEmpCheckTestNeg2() {
		assertEquals("No such employee Id exists", userServiceImpl.authEmpCheck(1244, "xyz"));
	}
	
	//successful
	@Test
	@Order(3)
	void renewPasswordCheck() {
		assertTrue(userServiceImpl.renewPassword(178, "yrs"));
	}
	
	//successful
	@Test
	@Order(2)
	void addEmployeeCheck() {
		LocalDate start = LocalDate.of(2000, 9, 12);
		LocalDate dep = LocalDate.of(2012, 9, 18);
		Employee emp = new Employee(178, "", "h", "h", "h", "h", start, dep, 0, 13);
		assertFalse(userServiceImpl.addEmployee(emp));
	}
	
	//successful
	@Test
	@Order(1)
	void  addEmployeeCheckPos() {
		LocalDate start = LocalDate.of(2000, 9, 12);
		LocalDate dep = LocalDate.of(2012, 9, 18);
		Employee emp = new Employee(178, "", "h", "h", "h", "h", start, dep, 0, 13);
		assertTrue(userServiceImpl.addEmployee(emp));
	}
	
	//successful
	@Test
	@Order(7)
	void getAllEmployeesCheck() {
		List<Employee> allExpectedEmps = new ArrayList<>();
		LocalDate start = LocalDate.of(2000, 9, 12);
		LocalDate dep = LocalDate.of(2012, 9, 18);
		Employee myEmp = new Employee(178, "$2a$10$.MFmjC2onOaNMbOC19y0o..8l/onE/WnSRCefNmINvxGLKJcy9aOW", "h", "h", "h", "h", start, dep, 0, 13);
		allExpectedEmps.add(myEmp);
		assertEquals(allExpectedEmps, userServiceImpl.allEmployees());
		
	}
	
	
}
