package com.example.birthday.controller;

import com.example.birthday.UserDto;
import com.example.birthday.repository.EmployeeRepository;
import com.example.birthday.services.Person;
import com.example.birthday.services.PersonService;
import com.example.birthday.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ui")
public class ViewPersonController {

    @Autowired
    EmployeeRepository employeeRepo;
    private final PersonService personService;
    private final UserService userService;

//    @Autowired
//    ActiveUserStore activeUserStore;

    public ViewPersonController(PersonService personService, UserService userService) {
        this.personService = personService;
        this.userService = userService;
    }

    @GetMapping("/personList")
    public String getPersons(Model model) {
        List<Person> personList = personService.getPeople();
        model.addAttribute("people", personList);
        return "personList";
    }

    //Create a new Person
    @GetMapping("/insertPerson")
    @Secured("ROLE_ADMIN")
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
    public String getEmployeeById(@PathVariable String id) {
        return id;
    }

    //Update and delete Person
    @PostMapping("/updatePerson/{id}")
    public String updatePerson(@PathVariable Long id,
                               @ModelAttribute("person") Person person,
                               Model model) {

        Optional<Person> existing = personService.getSinglePerson(id);
        existing.ifPresentOrElse(existingPerson -> {
                    var p = new Person(existingPerson.getId(), person.getGivenName(), person.getFamilyName(), person.getDateOfBirth(), person.getEmail(), person.getPassword(), person.getUsername());
                    personService.updatePerson(p);
                },
                () -> {
                    // TODO - add error message to spring model with flash
                    throw new RuntimeException("Data not found for person" + id);
                });

        return "redirect:ui/personList";
    }

    @GetMapping("/editPerson/{id}")
    @Secured("ROLE_ADMIN")
    public String editPerson(@PathVariable Long id, @ModelAttribute("person") Person person,
                             Model model) {
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

    @GetMapping("/personList/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return "redirect:/ui/personList";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, @ModelAttribute("person") Person person,
                          Model model) {
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

    @GetMapping("/showingUpcomingBirthdays")
    public String getUpcomingBirthdays(Person person, Model model, @RequestParam(value = "daysFromNow", required = false) Integer daysFromNow) {
        List<Person> personList = personService.findWithUpcomingBirthday(
                Optional.ofNullable(daysFromNow).orElse(30));
        model.addAttribute("people", personList);
        model.addAttribute("daysFromNow", daysFromNow);
        return "/showingUpcomingBirthdays";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(UserDto userDto, WebRequest request, Model model) {
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userService.findUser(userDto.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "userDto.email", "An account already exists for this email.");
            model.addAttribute("registration.html", userDto);
            return "registration.html";
        }
        userService.register(userDto);
        return "redirect:/ui/personList";
    }

//    @Controller
//    public class UserController {
//
//        @Autowired
//        ActiveUserStore activeUserStore;
//
//        @GetMapping("/loggedUsers")
//        public String getLoggedUsers(Locale locale, Model model) {
//            model.addAttribute("users", activeUserStore.getUsers());
//            return "users";
//        }
//    }


}
