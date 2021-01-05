package com.bloom.demo.model.patient;

import lombok.Data;

@Data
public class PatientRoomDetails {

    private String patientName, patientRoomType, patientAssignedDoctorName;
    private Gender gender;
    private Long patientRoomNumber;

}
