package com.example.birthday.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name ="employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "employee_id")
    private Long employeeId;

    @NotBlank(message = "Given Name is mandatory")
    @Column (name= "given_name")
    private String givenName;

    @NotBlank(message = "Family Name is mandatory")
    @Column (name= "family_Name")
    private String familyName;

    @NotNull(message = "Date Of Birth is mandatory")
    @Column (name= "date_of_birth")
    private LocalDate dateOfBirth;

    public String getEmail() {
        return email;
    }

    @NotBlank(message = "Email is mandatory")
    private String email;

    public String getPassword() {
        return password;
    }

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Username is mandatory")
    private String username;

    public EmployeeEntity(long l, String delia, String neagu, LocalDate of) {
    }



}
