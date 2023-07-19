package com.example.Birthday_processor2;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {

        @ValidEmail
        @NotNull
        @NotEmpty


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

        @NotNull
        @NotEmpty
        private String email;

        // standard getters and setters
    }

