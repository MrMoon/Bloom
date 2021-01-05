package com.bloom.demo.model.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
public class Doctor extends Employee {

    private String doctorAvailableDays;
    @Transient
    private ArrayList<DoctorAvailableTimes> doctorAssociatedTimes = new ArrayList<>();

    public Doctor addAssociatedTime(DoctorAvailableTimes doctorAvailableTimes) {
        this.doctorAssociatedTimes.add(doctorAvailableTimes);
        return this;
    }

    public Doctor removeAssociatedTime(DoctorAvailableTimes doctorAvailableTimes) {
        this.doctorAssociatedTimes.remove(doctorAvailableTimes);
        return this;
    }

}
