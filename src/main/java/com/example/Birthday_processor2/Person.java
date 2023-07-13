package com.example.Birthday_processor2;

import lombok.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Data
@NoArgsConstructor

public class Person {

    private Long id;
    private String givenName;
    private String familyName;
    private LocalDate dateOfBirth;

    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.NONE)
    private Clock clock = Clock.systemUTC();

    public Person(Long id, String givenName, String familyName, LocalDate dateOfBirth) {
        this.id = id;
        this.givenName = givenName;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
    }

    public long getDaysTillBirthday() {
        LocalDate now = LocalDate.now(this.clock);
        LocalDate birthday = LocalDate.of(now.getYear(), dateOfBirth.getMonthValue(), dateOfBirth.getDayOfMonth());
        long totalNumberOfDays = ChronoUnit.DAYS.between(now, birthday);

        return totalNumberOfDays;
    }

    public String getGeneration() {
        String generation;
        generation = null;
        if ((this.getDateOfBirth().getYear() <= 1924) && (this.getDateOfBirth().getYear() >= 1901)) {
            generation = "The greatest generation";
        } else if ((this.getDateOfBirth().getYear() <= 1945) && (this.getDateOfBirth().getYear() >= 1925)) {
            generation = "Silent Generation";
        } else if (this.getDateOfBirth().getYear() <= 1964 && this.getDateOfBirth().getYear() >= 1946) {
            generation = "Baby Boomer";
        } else if (this.getDateOfBirth().getYear() <= 1965 && this.getDateOfBirth().getYear() >= 1979) {
            generation = "Generation X";
        } else if (this.getDateOfBirth().getYear() <= 1994 && this.getDateOfBirth().getYear() >= 1980) {
            generation = "Millennials";
        } else if (this.getDateOfBirth().getYear() <= 2012 && this.getDateOfBirth().getYear() >= 1995) {
            generation = "Generation Z";
        } else if (this.getDateOfBirth().getYear() <= 2025 && this.getDateOfBirth().getYear() >= 2013) {
            generation = "Generation Alpha";
        }
        return generation;
    }

    public long getAge() {
        var age = ChronoUnit.YEARS.between(this.getDateOfBirth(), LocalDate.now());
        return age;
    }
}