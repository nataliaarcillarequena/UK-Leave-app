package com.employee.resource;

import com.employee.entity.employee;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
public class employeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "employees/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<employee> allEmpoyees(){
        return employeeService.getAllEmployees();
    }

    @RequestMapping(path = "employees", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addEmployee(@RequestBody employee emp){
        System.out.println(emp.);
        if(employeeService.insertEmployee(emp)){
            return "Employee added";
        }else{
            return "Employee not added";
        }
    }
}
