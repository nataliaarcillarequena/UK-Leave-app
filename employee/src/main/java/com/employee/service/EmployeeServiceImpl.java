package com.employee.service;

import com.employee.entity.employee;
import com.employee.persistence.employeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private employeeDao employeeDao;

    //transaction

    @Override
    public boolean insertEmployee(employee emp) {
        List<employee> allEmps = employeeDao.findAll(Sort.by(Sort.Direction.DESC, "empNo"));
        int lastEmpId = allEmps.get(0).getEmpNo();
        emp.setEmpNo(lastEmpId + 1);

        try {
            employeeDao.insertEmployee(emp.getEmpNo(), emp.getFullname(), emp.getSquad(), emp.getRole(), emp.getLocation(), emp.getStartDate(), emp.getDeployedDate(), emp.getLeaveEntitlemnt());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean DeleteEmployee(int empNo) {
        return employeeDao.deleteEmployee(empNo)>0;
    }

    //getting

    @Override
    public List<employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public employee getEmployeeByEmpNo(int empNo) {
        return employeeDao.searchEmployeeByEmpNo(empNo);
    }

    //updates

    @Override
    public boolean updateEmployee(int empNo, employee updatedEmp) {
        return false;
    }

    @Override
    public boolean updatePass(int empNo, String newPass) {
        return false;
    }
}
