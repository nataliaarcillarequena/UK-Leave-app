package com.user.service;

import java.util.List;

import com.user.entity.Employee;

public interface UserService {
	
	/*login/user authentication service*/
	String authEmpCheck(int empNo, String pass);
	
	/*user registration or use asks for renewing password*/
	Boolean renewPassword(int empNo, String pass);
	
	/*all employees and their details displayed to the admin*/
	List<Employee> allEmployees();
	
	/*admin inserts an employee into the DB- to be able to use this
	 * leave app*/
	Boolean addEmployee(Employee emp);
	
	
}
