package com.bloom.demo.model.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
public class Doctor extends Employee{

    private String doctorAvailableDays;
    private ArrayList<LocalDate> doctorAssociatedTimes = new ArrayList<>();

    public Doctor addAssociatedTime(LocalDate localDate) {
        this.doctorAssociatedTimes.add(localDate);
        return this;
    }

    public Doctor removeAssociatedTime(LocalDate localDate) {
        this.doctorAssociatedTimes.remove(localDate);
        return this;
    }

}
