package com.user.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import com.user.entity.Employee;
import com.user.service.UserServiceImpl;

@SpringBootTest
class LeaveMvc1ApplicationTests {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	//successful
	@Test
	void authEmpCheckTest() {
		assertEquals("Authenticated User", userServiceImpl.authEmpCheck(123, "xyz"));
	}
	
	//successful
	@Test
	void authEmpCheckTestNeg() {
		assertEquals("Incorrect Password", userServiceImpl.authEmpCheck(124, "xyz"));
	}

	//successful
	@Test
	void authEmpCheckTestNeg2() {
		assertEquals("No such employee Id exists", userServiceImpl.authEmpCheck(1244, "xyz"));
	}
	
	//successful
	@Test
	void renewPasswordCheck() {
		assertTrue(userServiceImpl.renewPassword(124, "yrs"));
	}
	
	//successful
	@Test
	void addEmployeeCheck() {
		LocalDate start = LocalDate.of(2000, 9, 12);
		LocalDate dep = LocalDate.of(2012, 9, 18);
		Employee emp = new Employee(178, "", "h", "h", "h", "h", start, dep, 0, 13);
		assertFalse(userServiceImpl.addEmployee(emp));
	}
	
	
	
}
