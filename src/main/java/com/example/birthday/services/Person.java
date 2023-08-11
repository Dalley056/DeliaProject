package com.example.birthday.services;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Person(
        Long id,
        String givenName,
        String familyName,
        LocalDate dateOfBirth,
        String email,
        String password,
        String username) implements Serializable {

     public Person() {
        this(0L, "", "", LocalDate.now(), "", "", "");
    }

    public Person(Long id, String givenName, String familyName, LocalDate dateOfBirth) {
        this(id, givenName, familyName, dateOfBirth, "", "", "");
    }

    public long getDaysTillBirthday() {
        LocalDate now = LocalDate.now(Clock.systemUTC());
        LocalDate birthday = LocalDate.of(now.getYear(), dateOfBirth.getMonthValue(), dateOfBirth.getDayOfMonth());
        return ChronoUnit.DAYS.between(now, birthday);
    }

    public String getGeneration() {
        String generation = "Nothing special";
        if ((dateOfBirth.getYear() <= 1924) && (dateOfBirth.getYear() >= 1901)) {
            generation = "The greatest generation";
        } else if ((dateOfBirth.getYear() <= 1945) && (dateOfBirth.getYear() >= 1925)) {
            generation = "Silent Generation";
        } else if (dateOfBirth.getYear() <= 1964 && dateOfBirth.getYear() >= 1946) {
            generation = "Baby Boomer";
        } else if (dateOfBirth.getYear() <= 1965 && dateOfBirth.getYear() >= 1979) {
            generation = "Generation X";
        } else if (dateOfBirth.getYear() <= 1994 && dateOfBirth.getYear() >= 1980) {
            generation = "Millennials";
        } else if (dateOfBirth.getYear() <= 2012 && dateOfBirth.getYear() >= 1995) {
            generation = "Generation Z";
        } else if (dateOfBirth.getYear() <= 2025 && dateOfBirth.getYear() >= 2013) {
            generation = "Generation Alpha";
        }
        return generation;
    }

    public long getAge() {
        return ChronoUnit.YEARS.between(this.dateOfBirth(), LocalDate.now());
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String givenName() {
        return givenName;
    }

    @Override
    public String familyName() {
        return familyName;
    }

    @Override
    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String username() {
        return username;
    }
}
