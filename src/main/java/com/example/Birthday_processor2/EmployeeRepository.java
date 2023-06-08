package com.example.Birthday_processor2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(value = "SELECT * FROM EMPLOYEES WHERE FORMATDATETIME(DATE_OF_BIRTH,'MMdd') BETWEEN :from AND :to",
                    nativeQuery= true)

    List<EmployeeEntity> findAllByMonthDayRange(@Param("from") String from, @Param("to") String to);
}
