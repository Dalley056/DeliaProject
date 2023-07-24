package com.example.Birthday_processor2;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class UserDto {


        @NotNull
        @NotEmpty
        private String email;

        @jakarta.validation.constraints.NotNull
        @NotEmpty
        private String firstName;

        @jakarta.validation.constraints.NotNull
        @NotEmpty
        private String lastName;

        @jakarta.validation.constraints.NotNull
        @NotEmpty
        private String password;
        private String matchingPassword;


        // standard getters and setters
    }

