package com.example.Birthday_processor2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryJpaTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void test() {

        EmployeeEntity employee = new EmployeeEntity(null, "Martin", "Smith", LocalDate.of(1975, 12, 14));

        testEntityManager.persist(employee);

        LocalDate fromDate = LocalDate.of(2023, 12, 1);
        int fromDay = fromDate.getDayOfYear();
        int toDay = fromDate.plusDays(60).getDayOfYear();
//        employeeRepository.findByDayOfYearRange(fromDay, toDay);


    }

}