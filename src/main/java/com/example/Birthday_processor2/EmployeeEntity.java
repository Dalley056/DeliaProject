package com.example.Birthday_processor2;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "First name is mandatory")
    @Column (name= "first_name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column (name= "last_name")
    private String lastName;

    @NotBlank(message = "Date Of Birth is mandatory")
    @Column (name= "date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Username is mandatory")
    private String userName;

}
