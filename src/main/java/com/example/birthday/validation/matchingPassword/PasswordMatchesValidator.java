//package com.example.birthday.validation;
//
//import com.example.birthday.UserDto;
//import jakarta.validation.ConstraintValidator;
//
//    public class PasswordMatchesValidator
//            implements ConstraintValidator<PasswordMatches, Object> {
//
//        @Override
//        public void initialize(PasswordMatches constraintAnnotation) {
//        }
//        @Override
//        public boolean isValid(Object obj, ConstraintValidatorContext context){
//            UserDto user = (UserDto) obj;
//            return user.getPassword().equals(user.getMatchingPassword());
//        }
//    }
//