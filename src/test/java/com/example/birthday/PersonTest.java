package com.example.birthday;

import com.example.birthday.services.Person;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;


class PersonTest {

    private Clock clock = Clock.system(ZoneId.systemDefault());

    @Test
    void testFindNumberOfDays() {
        Person martinEmp = new Person(1L, "Martin", "Smith", LocalDate.of(1975, 05, 18));
        martinEmp.setClock(Clock.fixed(Instant.parse("2023-05-15T12:30:59Z"), ZoneId.systemDefault()));
        assertThat(martinEmp.getDaysTillBirthday()).isEqualTo(3);
    }

    @Test
    public void testGetGeneration() {
        Person Delia = new Person(null, "Delia", "Neagu", LocalDate.of(2001, 12, 4));
        String generation = null;
        if ((Delia.getDateOfBirth().getYear() <= 1924) && (Delia.getDateOfBirth().getYear() >= 1901)) {
            generation = "The greatest generation";
        } else if ((Delia.getDateOfBirth().getYear() <= 1945) && (Delia.getDateOfBirth().getYear() >= 1925)) {
            generation = "Silent Generation";
        } else if (Delia.getDateOfBirth().getYear() <= 1964 && Delia.getDateOfBirth().getYear() >= 1946) {
            generation = "Baby Boomer";
        } else if (Delia.getDateOfBirth().getYear() <= 1965 && Delia.getDateOfBirth().getYear() >= 1979) {
            generation = "Generation X";
        } else if (Delia.getDateOfBirth().getYear() <= 1994 && Delia.getDateOfBirth().getYear() >= 1980) {
            generation = "Millennials";
        } else if (Delia.getDateOfBirth().getYear() <= 2012 && Delia.getDateOfBirth().getYear() >= 1995) {
            generation = "Generation Z";
        } else if (Delia.getDateOfBirth().getYear() <= 2025 && Delia.getDateOfBirth().getYear() >= 2013) {
            generation = "Generation Alpha";
        }

        assert generation == "Generation Z";
    }

    @Test
    @SuppressWarnings("checkstyle:javadocmethod")
    public void testGetAge() {
        Person Delia = new Person(null, "Delia", "Neagu", LocalDate.of(2001, 12, 4));
        var age = ChronoUnit.YEARS.between(Delia.getDateOfBirth(), LocalDate.now());
        assert age == 21;
    }

    //When new person is inserted it does not show the generation.
}
