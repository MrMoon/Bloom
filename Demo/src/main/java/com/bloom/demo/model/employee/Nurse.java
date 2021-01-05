package com.bloom.demo.model.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Nurse extends Employee {

    private Rank nurseRank;
    private Shift nurseShift;

}
