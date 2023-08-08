package com.employee.persistence;

import com.employee.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface employeeDao extends JpaRepository<employee, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into employee values(:empNo, :pass, :fullName, :squad, :role, :location, :startDate, :deployedDate, :leaveEntitlement", nativeQuery = true)
    public int insertSalesRecord(@Param("empNo") int empNo, @Param("fullName") String fullName, @Param("squad") String squad, @Param("role") String role, @Param("location") String location,
                                 @Param("startDate") LocalDate startDate, @Param("deployedDate") LocalDate deployedDate, @Param("leaveEntitlement") int leaveEntitlemnt);

}
