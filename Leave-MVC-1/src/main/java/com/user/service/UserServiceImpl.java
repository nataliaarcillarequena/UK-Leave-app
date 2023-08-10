package com.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entity.AuthEmployee;
import com.user.entity.Employee;

@Service
public class UserServiceImpl implements UserService {

	@Autowired RestTemplate restTemplate;

	/*
	 * returns the result from the users login attempt:
	 * 1. successful = "Authenticated User"
	 * 2. employee Id does not exist in the DB = "No such employee Id exists"
	 * 3. employee Id exists but password is wrong = "Incorrect Password"
	 */
	@Override
	public String authEmpCheck(int empNo, String pass) {
		
		AuthEmployee authEmployee = restTemplate.getForObject("http://localhost:8081/employee/"+empNo+"/"+pass, AuthEmployee.class);
		return authEmployee.getAuthStatus();
	}

	/*
	 * updating password field in employee record for when an employee
	 * registers/ forgets their password and renews it 
	 * returns true after password has been updated (and inputted hashed in the DB)
	 */
	@Override
	public Boolean renewPassword(int empNo, String pass) {
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String reg = restTemplate.exchange("http://localhost:8081/register/"+empNo+"/"+pass, HttpMethod.PUT, entity, String.class).getBody();
		
		if(reg.equals("Password encrypted"))
		 return true;
		
		return false;
	}

	/*
	 * returns a list of all the records in the employee dataset
	 * admin is able to see all employees on a screen 
	 * INFO: STILL TO TEST
	 */
	@Override
	public List<Employee> allEmployees() {
		
		Employee[] allEmps = restTemplate.getForObject("http://localhost:8081/employees/all", Employee[].class);
		List<Employee> employeeList = new ArrayList<>();
		
		for(Employee e:allEmps) {
			employeeList.add(e);
		}
		
		return employeeList;
	}

	/*
	 * adds employee into the DB and returns true 
	 * OR returns false when employee is not added i.e. when the given employee number 
	 * already exists in the DB 
	 */
	@Override
	public Boolean addEmployee(Employee emp) {
		
		String empAdded = restTemplate.postForObject("http://localhost:8081/employees", emp, String.class);
		if(empAdded.equals("Employee not added"))
			return false;
		
		return true;
	}
	
}
