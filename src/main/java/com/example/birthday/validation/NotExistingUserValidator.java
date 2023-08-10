//package com.example.birthday.validation;
//
//import com.example.birthday.UserDto;
//import com.example.birthday.services.PersonService;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import com.example.birthday.EmployeeEntity.Email;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class NotExistingUserValidator implements ConstraintValidator<NotExistingUser, UserDto> {
//
//    private final PersonService personService;
//
//    @Autowired
//    public NotExistingUserValidator(PersonService personService) { //<.>
//        this.personService = personService;
//    }
//
//    public void initialize(NotExistingUser constraint) {
//        // intentionally empty
//    }
//
//    // tag::isValid[]
//    public boolean isValid(UserDto formData, ConstraintValidatorContext context) {
//        if (personService.userWithEmailExists(new Email(formData.getEmail()))) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("{UserAlreadyExisting}")
//                    .addPropertyNode("email")
//                    .addConstraintViolation();
//
//            return false;
//        }
//
//        return true;
//    }
//    // end::isValid[]
//}