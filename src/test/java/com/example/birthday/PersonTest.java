package com.example.birthday;

import com.example.birthday.services.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


class PersonTest {

    @Test
    void testFindNumberOfDays() {
        Person martinEmp = new Person(1L, "Martin", "Smith", LocalDate.of(1975, 5, 18));
//        martinEmp.setClock(Clock.fixed(Instant.parse("2023-05-15T12:30:59Z"), ZoneId.systemDefault()));
        assertThat(martinEmp.getDaysTillBirthday()).isEqualTo(3);
    }

    @Test
    public void testGetGeneration() {
        Person Delia = new Person(null, "Delia", "Neagu", LocalDate.of(2001, 12, 4));
        String generation = null;
        if ((Delia.dateOfBirth().getYear() <= 1924) && (Delia.dateOfBirth().getYear() >= 1901)) {
            generation = "The greatest generation";
        } else if ((Delia.dateOfBirth().getYear() <= 1945) && (Delia.dateOfBirth().getYear() >= 1925)) {
            generation = "Silent Generation";
        } else if (Delia.dateOfBirth().getYear() <= 1964 && Delia.dateOfBirth().getYear() >= 1946) {
            generation = "Baby Boomer";
        } else if (Delia.dateOfBirth().getYear() <= 1965 && Delia.dateOfBirth().getYear() >= 1979) {
            generation = "Generation X";
        } else if (Delia.dateOfBirth().getYear() <= 1994 && Delia.dateOfBirth().getYear() >= 1980) {
            generation = "Millennials";
        } else if (Delia.dateOfBirth().getYear() <= 2012 && Delia.dateOfBirth().getYear() >= 1995) {
            generation = "Generation Z";
        } else if (Delia.dateOfBirth().getYear() <= 2025 && Delia.dateOfBirth().getYear() >= 2013) {
            generation = "Generation Alpha";
        }

        assert (Objects.equals(generation, "Generation Z"));
    }

    @Test
    @SuppressWarnings("checkstyle:javadocmethod")
    public void testGetAge() {
        Person Delia = new Person(null, "Delia", "Neagu", LocalDate.of(2001, 12, 4));
        var age = ChronoUnit.YEARS.between(Delia.dateOfBirth(), LocalDate.now());
        assert age == 21;
    }

    //When new person is inserted it does not show the generation.
}
