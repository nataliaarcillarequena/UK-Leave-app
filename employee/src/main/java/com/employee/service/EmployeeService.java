package com.employee.service;

import com.employee.entity.employee;

import java.util.List;

public interface EmployeeService {

    public boolean insertEmployee(employee emp);

    public boolean DeleteEmployee(int empNo);

    public List<employee> getAllEmployees();

    public boolean updateEmployee(int empNo, employee updatedEmp);

    public employee getEmployeeByEmpNo(int empNo);

    public boolean updatePass(int empNo, String newPass);


}
