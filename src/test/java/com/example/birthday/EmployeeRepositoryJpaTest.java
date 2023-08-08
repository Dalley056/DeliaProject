package com.example.birthday;

import com.example.birthday.repository.EmployeeEntity;
import com.example.birthday.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

@DataJpaTest
class EmployeeRepositoryJpaTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void test() {

        EmployeeEntity employee = new EmployeeEntity(0L, "Martin", "Smith", LocalDate.of(1975, 12, 14));

        testEntityManager.persist(employee);

        LocalDate fromDate = LocalDate.of(2023, 12, 1);
        int fromDay = fromDate.getDayOfYear();
        int toDay = fromDate.plusDays(60).getDayOfYear();
//        employeeRepository.findByDayOfYearRange(fromDay, toDay);


    }

}
