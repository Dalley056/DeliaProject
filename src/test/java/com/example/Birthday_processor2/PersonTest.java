package com.example.Birthday_processor2;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;


class PersonTest {

      @Test
      void testFindNumberOfDays(){
        Person martinEmp = new Person(1L, "Martin", "Smith", LocalDate.of(1975, 05, 18));
        martinEmp.setClock(Clock.fixed(Instant.parse("2023-05-15T12:30:59Z"), ZoneId.systemDefault()));
        assertThat(martinEmp.getDaysTillBirthday()).isEqualTo(3);
}
}