package com.bloom.employee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.BitSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class Doctor extends Employee {

    private String doctorAvailableDays;
    @Transient
    private ArrayList<LocalDate> doctorAssociatedTimes = new ArrayList<>();

}
