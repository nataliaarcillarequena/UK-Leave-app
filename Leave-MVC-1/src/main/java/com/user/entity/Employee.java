package com.user.entity;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

		 private int empNo;
		 private String pass;
		 private String fullname;
		 private String squad;
		 private String role;
		 private String location;
		 private LocalDate startDate;
		 private LocalDate deployedDate;
		 private int leaveEntitlment;
		 private int managerEmpNo;
		

}
