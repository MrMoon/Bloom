package com.bloom.demo.model.hospital;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FeePatient {

    private Long feePaymentNumber;
    private String patientName;
    private Double feeAmount;
    private LocalDate feeDate;

}
