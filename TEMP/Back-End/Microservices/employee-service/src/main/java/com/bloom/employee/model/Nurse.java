package com.bloom.employee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Nurse extends Employee {

    private Rank nurseRank;
    private Shift nurseShift;

}
