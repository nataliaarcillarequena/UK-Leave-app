package com.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class employee {

    @Id
    private int empNo;

    //may change for Security
    private String pass;

    private String fullname;
    private String squad;
    private String role;
    private String location;
    private LocalDate startDate;
    private LocalDate deployedDate;
    private int LeaveEntitlemnt;
    private int managerEmpNo;
}
