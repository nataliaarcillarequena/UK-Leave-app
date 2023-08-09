package com.employee.entity;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

	 @Id
	 private int empNo;
	 private String pass;
	 private String fullname;
	 private String squad;
	 private String role;
	 private String location;
	 private LocalDate startDate;
	 private LocalDate deployedDate;
	 private int leaveEntitlemnt;
	 private int managerEmpNo;
	
}
