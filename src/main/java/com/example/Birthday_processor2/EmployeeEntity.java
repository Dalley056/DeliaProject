package com.example.Birthday_processor2;

import jakarta.persistence.*;
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

    @Column (name= "first_name")
    private String firstName;

    @Column (name= "last_name")
    private String lastName;

    @Column (name= "date_of_birth")
    private LocalDate dateOfBirth;

}
