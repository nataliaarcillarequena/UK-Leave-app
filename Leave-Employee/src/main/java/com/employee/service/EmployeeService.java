package com.employee.service;

import java.util.List;

import com.employee.entity.AuthEmployee;
import com.employee.entity.Employee;

public interface EmployeeService {

	/*authenticates employees and gets their details when logging in */
	AuthEmployee authenticateEmployee(int empNumb, String pass);
	
	/*updates employees password when employee is registering/forgetting password*/
	Boolean registerEmployee(int empNumb, String pass);
	
	/*gets the employees role for authorisation purposes- FOR NOW DONT NEED*/
	String getRoleByEmpNumber(int empNumb);
	
	/* insert employee record with the encoded password*/
	//Boolean registerEmployee(int empNumb);
	
	/*return all the employee records*/
	public List<Employee> getAllEmployees();
	
	/*insert employee record*/
	public boolean insertEmployee(Employee emp);
	
}
