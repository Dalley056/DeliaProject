package com.example.birthday.controller;

import com.example.birthday.services.Person;
import com.example.birthday.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people")
     public List<Person> getEmployees(){
         return personService.getPeople();
        }

    @PostMapping("/people")
    public Person saveEmployee (@RequestBody Person person){

        return personService.savePerson(person);

    }

    @GetMapping("/employees/{id}")
    public Optional<Person> getEmployee (@PathVariable Long id) {
        return personService.getSinglePerson(id);
    }


}
