package com.example.Birthday_processor2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ui")
public class ViewPersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/personList")
  public String getPersons(Model model){
        List<Person> personList= personService.getPeople();
        model.addAttribute("people",personList);
        return "personList";
    }

//Create a new Person
    @GetMapping("/insertPerson")
    public String createPersonForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "insertPerson";
    }

    @PostMapping("/personList")
    public String savePerson(@ModelAttribute("insertPerson") Person person) {
        personService.savePerson(person);
        return "redirect:/ui/personList";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable String id){
        return id;
    }

    //Update and delete Person
    @PostMapping("/updatePerson/{id}")
    public String updatePerson(@PathVariable Long id,
                                @ModelAttribute("person") Person person,
                                Model model) {

        Optional<Person> existing = personService.getSinglePerson(id);
        existing.ifPresentOrElse(existingPerson -> {
//                    existingPerson.setId(person.getId());
                    existingPerson.setGivenName(person.getGivenName());
                    existingPerson.setFamilyName(person.getFamilyName());
                    existingPerson.setDateOfBirth(person.getDateOfBirth());
                    personService.updatePerson(existingPerson);
                },
                        () -> {
            // TODO - add error message to spring model with flash
                    throw new RuntimeException("Data not found for person" + id);
                        });


        return "redirect:ui/personList";
        // get person from database by id
    }

    @GetMapping("/editPerson/{id}")
    public String editPerson(@PathVariable Long id, @ModelAttribute("person") Person person,
                             Model model){
        Optional<Person> existing = personService.getSinglePerson(id);
        existing.ifPresentOrElse(existingPerson -> {
          model.addAttribute("person", existingPerson);
                },
                () -> {
                    // TODO - add error message to spring model with flash
                    throw new RuntimeException("Data not found for person" + id);
                });


        return "editPerson";
    }


    // handler method to handle delete Person request
    @GetMapping("/personList/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return "redirect:/ui/personList";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id,@ModelAttribute("person") Person person,
                          Model model ) {
//        personService.getSinglePerson(id); //put in model and page access
//        model.addAttribute("person", person);
//        return "profile/{id}";

//        Optional<Person> profile= personService.getSinglePerson(id);
//        model.addAttribute("person",profile);
//        return "profile";
        Optional<Person> existing = personService.getSinglePerson(id);
        existing.ifPresentOrElse(existingPerson -> {
                    model.addAttribute("person", existingPerson);
                },
                () -> {
                    // TODO - add error message to spring model with flash
                    throw new RuntimeException("Data not found for person" + id);
                });


        return "profile";

    }

    // things after ? are called Query string parameter
    // http://localhost:8080/ui/showingUpcomingBirthdays?daysFromNow=10
    @GetMapping("/showingUpcomingBirthdays")
    public String getUpcomingBirthdays(Model model, @RequestParam(value="daysFromNow", required = false) Integer daysFromNow){
        // Call service with number of days from http call or none
        List<Person> personList= personService.findWithUpcomingBirthday(
                Optional.ofNullable(daysFromNow).orElse(30));
        model.addAttribute("people",personList);
        model.addAttribute("daysFromNow",daysFromNow);
        return "/showingUpcomingBirthdays";
    }

}

