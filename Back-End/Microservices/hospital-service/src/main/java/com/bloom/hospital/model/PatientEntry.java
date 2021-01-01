package com.bloom.hospital.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PatientEntry {

    @Id
    private Long patientEntryId;
    private String patientId;
    private PatientEntryType patientEntryType;

}
