package com.example.Birthday_processor2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

class GetTheUpcomingBirthdaysTest {



    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Person Delia= new Person(null,"Delia", "Neagu", LocalDate.of(2001,12,4));
        Person Joe =new Person(null, "Joe", "Smith", LocalDate.of(1990,05,12));
        Person Jenna= new Person(null, "Jenna", "Ortega", LocalDate.of(1980, 5, 24) );

        LocalDate localDate = LocalDate.now().plusDays(50);

    }
}