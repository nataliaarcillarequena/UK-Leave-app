package com.employee.persistence;

import com.employee.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface employeeDao extends JpaRepository<employee, Integer> {

/*
    @Modifying
    @Transactional
    @Query(value = "insert into employee values(:empNo, :pass, :fullName, :squad, :role, :location, :startDate, :deployedDate, :leaveEntitlement", nativeQuery = true)
    public int insertEmployee(@Param("empNo") int empNo, @Param("fullName") String fullName, @Param("squad") String squad, @Param("role") String role, @Param("location") String location,
                                 @Param("startDate") LocalDate startDate, @Param("deployedDate") LocalDate deployedDate, @Param("leaveEntitlement") int leaveEntitlemnt);
*/

    @Modifying
    @Transactional
    @Query(value = "insert into employee values(:empNo, :pass, :fullName, :squad, :role, :location, :startDate, :deployedDate, :leaveEntitlement, :managerEmpNo)", nativeQuery = true)
    public int insertEmployee(@Param("empNo") int empNo,@Param("pass") String pass, @Param("fullName") String fullName, @Param("squad") String squad,@Param("role")String role, @Param("location") String location,
                              @Param("startDate") LocalDate startDate, @Param("deployedDate") LocalDate deployedDate, @Param("leaveEntitlement") int leaveEntitlment, @Param("managerEmpNo") int managerEmpNo);

    @Modifying
    @Transactional
    @Query("delete from employee where empNo = :id")
    public int deleteEmployee(@Param("id")int empNo);

    public employee searchEmployeeByEmpNo(int empNo);
}
