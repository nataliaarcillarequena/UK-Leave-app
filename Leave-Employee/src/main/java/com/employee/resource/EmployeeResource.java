package com.employee.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.AuthEmployee;
import com.employee.entity.Employee;
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
	
	//resource testing the update method for user registration
	@PutMapping(path = "register/{empNo}/{pass}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String registerUserResource(@PathVariable("empNo") int empNo, @PathVariable("pass") String pass) {
		if(employeeService.registerEmployee(empNo, pass))
			return "Password encrypted";
		return "Password not encrypted";
	}
	
	 @GetMapping(path = "employees/all", produces = MediaType.APPLICATION_JSON_VALUE)
	 public List<Employee> allEmpoyees(){
		 return employeeService.getAllEmployees();
	    }
	
	 @RequestMapping(path = "employees", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE,
			    consumes = MediaType.APPLICATION_JSON_VALUE)
	 public String addEmployee(@RequestBody Employee emp){
		  if(employeeService.insertEmployee(emp)){
			  return "Employee added";
		  }else{
			  return "Employee not added";
		  }
	 }
	 
	 
}
