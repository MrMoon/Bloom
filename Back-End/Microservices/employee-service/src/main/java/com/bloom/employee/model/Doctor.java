package com.bloom.employee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class Doctor extends Employee {

    private char[] doctorAvailableDays = new char[7];
    private ArrayList<LocalDate> doctorAssociatedTimes = new ArrayList<>();

}
