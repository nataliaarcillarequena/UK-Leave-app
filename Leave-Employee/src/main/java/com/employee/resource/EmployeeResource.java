package com.employee.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.AuthEmployee;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;
	
	
	//get mapping to get the auth user
	@GetMapping(path="employee/{empNo}/{pass}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthEmployee empAuthResource(@PathVariable("empNo")int empNo, @PathVariable("pass") String pass) {
		
		return employeeService.authenticateEmployee(empNo, pass);
	}
	
	@PutMapping(path = "register/{empNo}/{pass}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String registerUserResource(@PathVariable("empNo") int empNo, @PathVariable("pass") String pass) {
		if(employeeService.registerEmployee(empNo, pass))
			return "Password encrypted";
		return "Password not encrypted";
	}
	
}
