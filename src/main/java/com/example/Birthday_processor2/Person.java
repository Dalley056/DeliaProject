package com.example.Birthday_processor2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@Data
@NoArgsConstructor

public class Person {

    private Long id;
    private String givenName;
    private String familyName;
    private LocalDate dateOfBirth;
}
