package com.bloom.demo.model.patient;

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
    private Gender gender;
    @Transient
    private Integer patientAge;

}
