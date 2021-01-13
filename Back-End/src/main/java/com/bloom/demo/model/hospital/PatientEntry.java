package com.bloom.demo.model.hospital;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PatientEntry {

    @Id
    private Long patientEntryId;
    private Long patientId, patientDoctorId, patientRoomNumber;
    private PatientEntryType patientEntryType;

}
