package com.employee.controller;

import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class employeeController {

    @Autowired
    private EmployeeService employeeService;
}
