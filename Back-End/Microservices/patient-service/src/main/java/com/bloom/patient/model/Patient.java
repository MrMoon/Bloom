package com.bloom.patient.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Patient {

    @Id
    private Long patientId;
    private Long patientRoomNumber , patientAssignedDoctorId;
    private String patientName;
    private Date patientDateOfBirth;
    private Double patientAge;
    private Boolean isAdmitted;

}
