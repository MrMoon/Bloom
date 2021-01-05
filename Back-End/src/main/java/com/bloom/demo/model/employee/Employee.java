package com.bloom.demo.model.employee;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;

@Data
public class Employee {

    @Id
    private Long employeeId;
    private String employeeName, employeeJobType;
    private Double employeeSalary;
    private LocalDate employeeDateOfBirth;
    @Transient
    private JobType jobType;


}
