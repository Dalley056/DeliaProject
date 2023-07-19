package com.example.Birthday_processor2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@PostMapping("/user/registration")
public class ModelAndView (@ModelAttribute("user") @Valid UserDto userDto,
                           HttpServletRequest request, Errors errors) {
    @ModelAttribute("user") @Valid UserDto userDto,
    HttpServletRequest request,
    Errors errors) {

        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        // rest of the implementation
    }
}
