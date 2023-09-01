package com.example.birthday.services;

import com.example.birthday.repository.EmployeeEntity;
import com.example.birthday.repository.EmployeeRepository;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;


@Service
public class PersonServiceImpl implements PersonService {

    private final EmployeeRepository employeeRepo;

    private Clock clock = Clock.system(ZoneId.systemDefault());

    public PersonServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @Override
    public void SetClock(Clock clock) {
        this.clock = clock;
    }

    public Person convertEmployeeEntityintoEmployee(EmployeeEntity entity) {
        return new Person(entity.getEmployeeId(), entity.getGivenName(), entity.getFamilyName(), entity.getDateOfBirth(), entity.getEmail(), entity.getPassword(), entity.getUsername());
    }

    @Override
    public List<Person> getPeople() {
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();

        return employeeEntities.stream().map(this::convertEmployeeEntityintoEmployee).toList();
    }


    @Override
    public Person savePerson(Person person) {
        EmployeeEntity entity = new EmployeeEntity(person.getId(), person.getGivenName(), person.getFamilyName(),
                person.getDateOfBirth(), person.getEmail(), person.getPassword(), person.getUsername());
        employeeRepo.save(entity);
        return convertEmployeeEntityintoEmployee(entity);
    }


    @Override
    public Optional<Person> getSinglePerson(Long id) {

        return employeeRepo.findById(id).map(this::convertEmployeeEntityintoEmployee);
    }

    @Override
    public List<Person> findWithUpcomingBirthday(int includeDaysFromNow) {

        LocalDate from = LocalDate.now(this.clock);
        LocalDate to = from.plusDays(includeDaysFromNow);

        int fromDayOfYear = from.getDayOfYear(); //8/11
        int toDayOfYear = to.getDayOfYear();     //2/01

        if (rangeSpansTwoYears(fromDayOfYear, toDayOfYear, to)) {
            int fromYearLastDay = LocalDate.of(from.getYear(), DECEMBER, 31).getDayOfYear();
            int toYearFirstDay = LocalDate.of(to.getYear(), JANUARY, 1).getDayOfYear();
            return employeeRepo.findWithDayOfYearAcrossTwoYears(fromDayOfYear, fromYearLastDay, toYearFirstDay, toDayOfYear)
                    .stream()
                    .map(this::convertEmployeeEntityintoEmployee)
                    .toList();
        } else {
            return employeeRepo.findWithDayOfYear(fromDayOfYear, toDayOfYear)
                    .stream()
                    .map(this::convertEmployeeEntityintoEmployee)
                    .toList();
        }

    }

    private static boolean rangeSpansTwoYears(int fromDayOfYear, int toDayOfYear, LocalDate toDate) {
        return (toDayOfYear < fromDayOfYear) || (toDayOfYear == fromDayOfYear && toDate.isLeapYear());
    }

    //

    @Override
    public void updatePerson(Person person) {
        EmployeeEntity entity = new EmployeeEntity(person.getId(), person.getGivenName(), person.getFamilyName(),
                person.getDateOfBirth(), person.getEmail(), person.getPassword(), person.getUsername());
        employeeRepo.save(entity);

    }

    @Override
    public void deletePersonById(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public int getNumberOfDaysUntilEmployeeBirthday(Person person) {
        String dateOfBirth = String.valueOf(person.getDateOfBirth());
        long dateOfBirth2 = Long.parseLong(dateOfBirth); //covert to long
        LocalDate now = LocalDate.now(this.clock);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendValue(MONTH_OF_YEAR, 2)
                .appendValue(DAY_OF_MONTH, 2)
                .toFormatter();
        int daysUntilBirthday = 0;
        int fromYearLastDay = LocalDate.of(now.getYear(), DECEMBER, 31).getDayOfYear();
        if (person.getDateOfBirth().getDayOfYear() < now.getDayOfYear()) {
            daysUntilBirthday = person.getDateOfBirth().getDayOfYear() + (fromYearLastDay - now.getDayOfYear());
            return daysUntilBirthday;
        } else {
            return Integer.parseInt(String.valueOf(now.minusDays(dateOfBirth2).format(formatter)));
        }
    }

    @Override
    public boolean userWithEmailExists(Email email) {
        return false;
    }


//    @Override
//    public boolean checkIfUserExist(String email) {
//        return employeeRepo.findByEmail(email) !=null ? true : false;
//    }


}
