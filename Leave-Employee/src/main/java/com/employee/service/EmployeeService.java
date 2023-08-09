package com.employee.service;

import com.employee.entity.AuthEmployee;

public interface EmployeeService {

	/*authenticates employees and gets their details when logging in */
	AuthEmployee authenticateEmployee(int empNumb, String pass);
	
	/*updates employees password when employee is registering/forgetting password*/
	Boolean registerEmployee(int empNumb, String pass);
	
	/*gets the employees role for authorisation purposes*/
	String getRoleByEmpNumber(int empNumb);
	
	/* insert employee record with the encoded password*/
	//Boolean registerEmployee(int empNumb);
	
	
}
