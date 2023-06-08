package com.example.Birthday_processor2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PersonRestController {
    @Autowired
    private PersonService personService;

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
