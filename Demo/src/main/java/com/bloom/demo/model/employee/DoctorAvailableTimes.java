package com.bloom.demo.model.employee;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DoctorAvailableTimes {

    @Id
    private Long tempId;
    private Long doctorId;
    private String timing;

}
