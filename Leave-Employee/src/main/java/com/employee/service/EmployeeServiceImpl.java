package com.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.entity.AuthEmployee;
import com.employee.entity.Employee;
import com.employee.persistence.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	/*
	 * authenticating user and fetching their details if they do exist in the DB,
	 * if users exists but their password is wrong then message indicates this
	 * if user does not exist in the DB the message indicates this
	 * Q: do i really need to fetch the employees details tho?
	 */
	@Override
	public AuthEmployee authenticateEmployee(int empNumb, String pass) {
		
		AuthEmployee empAuthenticated = new AuthEmployee();
		
		//BCrypt to match the encoded password in the DB to the password that the employee has provided
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		
		
		//findBy in JPA returns optional object (may be null)
		Optional<Employee> opEmployee = employeeDao.findById(empNumb);
		
		if(opEmployee.isPresent()) {
			Employee empLogin = opEmployee.get();
			
			if(bcrypt.matches(pass, empLogin.getPass())) {
				
				empAuthenticated.setEmp(empLogin);
				empAuthenticated.setAuthStatus("Authenticated User");
			}else {
				empAuthenticated.setEmp(null);
				empAuthenticated.setAuthStatus("Incorrect Password");
			}
			
			return empAuthenticated;
		}
		
		empAuthenticated.setEmp(null);
		empAuthenticated.setAuthStatus("No such employee Id exists");
		
		return empAuthenticated;
	}

	/*
	 * registering employee onto the system by hashing their password
	 * and storing in the DB as the encoded password (using spring
	 * security)
	 * returns true if successful
	 */
	@Override
	public Boolean registerEmployee(int empNumb, String pass) {

		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		return (employeeDao.updatePassword(empNumb, passEncoder.encode(pass))>0);
	}

	@Override
	public String getRoleByEmpNumber(int empNumb) {
		// TODO Auto-generated method stub
		return null;
	}

}
