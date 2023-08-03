package com.example.Birthday_processor2;

import java.time.Clock;
import java.util.List;
import java.util.Optional;

public interface PersonService {
    void SetClock(Clock clock);

    List<Person> getPeople();

    Person savePerson(Person person);

    Optional<Person> getSinglePerson(Long id);

    List<Person> findWithUpcomingBirthday(int includeDaysFromNow);


    void deletePersonById(Long id);

    void updatePerson(Person existingPerson);

    int getNumberOfDaysUntilEmployeeBirthday(Person person);

    void register(final UserDto user) throws UserAlreadyExistException;

    boolean checkIfUserExist(String email);
}
