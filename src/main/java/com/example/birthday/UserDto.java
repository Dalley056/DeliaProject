package com.example.birthday;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

//@PasswordMatches
public class UserDto implements Serializable {


        @NotBlank(message = "Please enter your first name")
        private String givenName;

        @NotBlank(message = "Please enter your last name")
        private String familyName;

        @NotBlank(message = "Please enter your username")
        private String username;

        @NotNull(message = "Please enter your date of birth")
        @DateTimeFormat
        private String dateOfBirth;

//        @ValidEmail

        @NotBlank(message = "Please enter your email")
        @Email(message = "Enter a valid email address")
        private String email;

//        @ValidPassword
        @NotBlank(message = "Please enter your password")
        @Length(min=8, message = "Passwords must be at least 8 characters long")
        private String password;
        @NotBlank(message = "Please enter your re-enter your password")
        private String matchingPassword;

        public String getgivenName() {
                return givenName;
        }

        public void setgivenName(String givenName) {
                this.givenName = givenName;
        }

        public String getfamilyName() {
                return familyName;
        }

        public void setfamilyName(String familyName) {
                this.familyName = familyName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
        }

        public String getMatchingPassword() {
                return matchingPassword;
        }

        public void setMatchingPassword(String dateOfBirth) {
                this.matchingPassword = matchingPassword;
        }
}
