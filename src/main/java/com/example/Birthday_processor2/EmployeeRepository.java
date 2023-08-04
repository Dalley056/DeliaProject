package com.example.Birthday_processor2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(value = "SELECT * FROM EMPLOYEES WHERE FORMATDATETIME(DATE_OF_BIRTH,'MMdd') BETWEEN :from AND :to",
            nativeQuery= true)

    List<EmployeeEntity> findAllByMonthDayRange(@Param("from") String from, @Param("to") String to);

//    @Query(value = "SELECT * FROM EMPLOYEES WHERE DAY_OF_YEAR(DATE_OF_BIRTH) BETWEEN :from AND :to",
//            nativeQuery= true)
//    List<EmployeeEntity> findByDayOfYearRange(int fromDay, int toDay);

    @Query(value = """
            SELECT * FROM EMPLOYEES 
            WHERE (DAY_OF_YEAR(DATE_OF_BIRTH) BETWEEN :fromDayOfYear AND :fromYearLastDay)
            OR
            (DAY_OF_YEAR(DATE_OF_BIRTH) BETWEEN :toYearFirstDay AND :toDayOfYear)
            """,
            nativeQuery= true)
    List<EmployeeEntity> findWithDayOfYearAcrossTwoYears(int fromDayOfYear, int fromYearLastDay, int toYearFirstDay, int toDayOfYear);

    @Query(value = """
            SELECT * FROM EMPLOYEES 
            WHERE (DAY_OF_YEAR(DATE_OF_BIRTH) BETWEEN :fromDayOfYear AND :toDayOfYear)
            """,
            nativeQuery= true)
    List<EmployeeEntity> findWithDayOfYear(int fromDayOfYear, int toDayOfYear);

    EmployeeEntity findByEmail(String email);


}
