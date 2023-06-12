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

    public long getDaysTillBirthday(){
        LocalDate now = LocalDate.now(this.clock);
        LocalDate birthday= LocalDate.of(now.getYear(),dateOfBirth.getMonthValue(),dateOfBirth.getDayOfMonth());
       long totalNumberOfDays =  ChronoUnit.DAYS.between(now,birthday);

       return totalNumberOfDays;
    }
}
