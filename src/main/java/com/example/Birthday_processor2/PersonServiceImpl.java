package com.example.Birthday_processor2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;


@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private EmployeeRepository employeeRepo;

    private Clock clock = Clock.system(ZoneId.systemDefault());

    @Override
    public void SetClock(Clock clock) {
        this.clock = clock;
    }

    public Person convertEmployeeEntityintoEmployee(EmployeeEntity entity) {
        return new Person(entity.getEmployeeId(), entity.getFirstName(), entity.getLastName(), entity.getDateOfBirth());
    }

    @Override
    public List<Person> getPeople() {
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();

        return employeeEntities.stream().map(this::convertEmployeeEntityintoEmployee).toList();
    }


    @Override
    public Person savePerson(Person person) {
        EmployeeEntity entity = new EmployeeEntity(person.getId(), person.getGivenName(), person.getFamilyName(), person.getDateOfBirth());
        employeeRepo.save(entity);
        return convertEmployeeEntityintoEmployee(entity);
    }


    @Override
    public Optional<Person> getSinglePerson(Long id) {

        return employeeRepo.findById(id).map(this::convertEmployeeEntityintoEmployee);
    }

    @Override
    public List<Person> findWithUpcomingBirthday(int includeDaysFromNow) {
        LocalDate now = LocalDate.now(this.clock);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(MONTH_OF_YEAR, 2)
                .appendValue(DAY_OF_MONTH, 2)
                .toFormatter();
        String dateMonthDay = now.format(formatter);
        String dateMonthDay30 = String.valueOf(now.plusDays(includeDaysFromNow).format(formatter));

        return employeeRepo.findAllByMonthDayRange(dateMonthDay, dateMonthDay30).stream()
                .map(this::convertEmployeeEntityintoEmployee)
                .toList();
    }

    @Override
    public void updatePerson(Person person) {
        EmployeeEntity entity = new EmployeeEntity(person.getId(), person.getGivenName(), person.getFamilyName(), person.getDateOfBirth());
        employeeRepo.save(entity);

    }

    @Override
    public void deletePersonById(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public int getNumberOfDaysUntilEmployeeBirthday(Person person){
        String dateOfBirth= String.valueOf(person.getDateOfBirth());
        Long dateOfBirth2= Long.valueOf(dateOfBirth); //covert to long
        LocalDate now = LocalDate.now(this.clock);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(MONTH_OF_YEAR, 2)
                .appendValue(DAY_OF_MONTH, 2)
                .toFormatter();
        int totalNumberOfDays = Integer.parseInt(String.valueOf(now.minusDays(dateOfBirth2).format(formatter)));

        return totalNumberOfDays;
    }
}
