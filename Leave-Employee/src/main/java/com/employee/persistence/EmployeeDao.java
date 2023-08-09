package com.employee.persistence;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	
	@Modifying
    @Transactional
    @Query(value = "insert into employee values(:empNo, :pass, :fullName, :squad, :role, :location, :startDate, :deployedDate, :leaveEntitlement, :managerId", nativeQuery = true)
    public int insertSalesRecord(@Param("empNo") int empNo, @Param("fullName") String fullName, @Param("squad") String squad, @Param("role") String role, @Param("location") String location,
                                 @Param("startDate") LocalDate startDate, @Param("deployedDate") LocalDate deployedDate, @Param("leaveEntitlement") int leaveEntitlemnt, @Param("managerId") int manaId);
	
	//updating the employees password
	@Modifying
	@Transactional
	@Query("update Employee set pass = :userPass where empNo = :empId")
	public int updatePassword(@Param("empId")int empNo, @Param("userPass") String pass);
	
	//finds and returns the employee record by employee number 
	public Optional<Employee> findById(int empNo);
	
	
	
}
