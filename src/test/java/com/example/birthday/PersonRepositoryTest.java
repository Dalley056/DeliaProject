package com.example.birthday;

import com.example.birthday.repository.EmployeeEntity;
import com.example.birthday.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Transactional
class PersonRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveEmployeeTest() {
        EmployeeEntity employeeEntity = new EmployeeEntity(0L, "Delia", "Neagu", of(2001, 12, 4));
        employeeRepository.save(employeeEntity);
        EmployeeEntity found = testEntityManager.find(EmployeeEntity.class, employeeEntity.getEmployeeId());
        assertThat(found.getEmployeeId()).isEqualTo(employeeEntity.getEmployeeId());
    }

    @Test
    public void getEmployeeByIDTest() {
        EmployeeEntity Delia = new EmployeeEntity(0L, "Delia", "Neagu", of(2001, 12, 4));
        testEntityManager.persist(Delia);
        Delia = employeeRepository.findById(Delia.getEmployeeId()).orElse(null);
        assertThat(Delia.getEmployeeId()).isEqualTo(Delia.getEmployeeId());
    }

    @Test
    public void getListOfEmployeesTest() {
        EmployeeEntity Delia = new EmployeeEntity(0L, "Delia", "Neagu", of(2001, 12, 4));
        testEntityManager.persist(Delia);
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        assertThat(employeeEntities).isNotEmpty();
    }


    @Test
    public void getTheUpcomingBirthdays() {
        EmployeeEntity delia = new EmployeeEntity(0L,"Delia", "Neagu", of(2001,5,4));
        EmployeeEntity joe = new EmployeeEntity(0L, "Joe", "Smith", of(1990,5,5));
        EmployeeEntity jenna = new EmployeeEntity(0L, "Jenna", "Ortega", of(1980, 5, 15) );
        EmployeeEntity julie = new EmployeeEntity(0L,"Julie", "Sky", of(2000,6,2));
        EmployeeEntity mark = new EmployeeEntity(0L, "Mark", "Kenth", of(1999,6,3));
        EmployeeEntity ariana = new EmployeeEntity(0L, "Ariana", "Grande", of(1970, 6, 4) );

        List<EmployeeEntity> employees = Arrays.asList(delia,joe,jenna,julie,mark,ariana);
        for (EmployeeEntity employee : employees) {
            testEntityManager.persist(employee);
        }

        List<EmployeeEntity> employeesInRange = employeeRepository.findAllByMonthDayRange("0505", "0603"); //0603
        assertThat(employeesInRange).containsExactlyInAnyOrder(joe, jenna, julie, mark);

}}
