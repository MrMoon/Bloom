package com.bloom.employee.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;

@Data
public class Employee {

    @Id
    private Long employeeId;
    private String employeeName , employeeJobType;
    @Transient
    private JobType jobType;
    private Double employeeSalary;
    private LocalDate employeeDateOfBirth;

}
