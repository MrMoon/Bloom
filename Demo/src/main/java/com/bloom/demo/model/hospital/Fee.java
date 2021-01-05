package com.bloom.demo.model.hospital;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

@Data
public class Fee {

    @Id
    private Long feePaymentNumber;
    private Long patientId;
    private Double feeAmount;
    private LocalDate feeDate = LocalDate.ofInstant(Calendar.getInstance().getTime().toInstant() , ZoneId.systemDefault());

}
