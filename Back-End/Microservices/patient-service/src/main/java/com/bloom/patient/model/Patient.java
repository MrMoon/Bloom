package com.bloom.patient.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;

@Data
public class Patient {

    @Id
    private Long patientId;
    private Long patientRoomNumber, patientAssignedDoctorId;
    private String patientName;
    private LocalDate patientDateOfBirth;
    private Boolean isAdmitted;
    @Transient
    private Integer patientAge;

}
