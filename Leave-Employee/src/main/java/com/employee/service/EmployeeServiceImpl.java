package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

	//gets all the employees
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.findAll();
	}

	/*
	 * insert employee into DB
	 * Nat: changed the functionality a bit- if adding a new employee
	 * then emp Number can come through blank (0) whereby we assign the employee number (max Emp Nu in DB + 1)
	 * otherwise the employee number comes through in the Employee object (e.g. when initiallly putting in the 
	 * employee records into the DB) 
	 */
	@Override
	public boolean insertEmployee(Employee emp) {
		List<Employee> allEmps = employeeDao.findAll(Sort.by(Sort.Direction.DESC, "empNo"));
        int lastEmpId = allEmps.get(0).getEmpNo();
        
        //changed here
        if(emp.getEmpNo()==0)
        	emp.setEmpNo(lastEmpId + 1);

        
        
        try {
        	//insert method is not doing its thing
            //employeeDao.insertEmployee(emp.getEmpNo(), null, emp.getFullname(), emp.getSquad(), emp.getRole(), 
            //		emp.getLocation(), emp.getStartDate(), emp.getDeployedDate(), emp.getLeaveEntitlment(), emp.getManagerEmpNo());
        	
        	//making sure to catch unique records being inserted into the DB 
        	if(employeeDao.findById(emp.getEmpNo()).isPresent()) {
        		return false;
        	}else {
        		employeeDao.save(emp);
        		return true;
        	}
        } catch (Exception e){
            return false;
        }
	}

}
