package com.bloom.hospital.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

@Data
public class Fee {

    @Id
    private Long feePaymentNumber;
    private String patientId;
    private Double feeAmount;
    private final LocalDate feeDate = LocalDate.ofInstant(Calendar.getInstance().getTime().toInstant() , ZoneId.systemDefault());

}
