package com.bloom.patient.kafka;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PatientProcessor {

    @Bean
    public Function<KStream<String, Byte>, KTable<String, Byte>> patientStateProcessor() {
        return stringPatientKStream -> stringPatientKStream
                .groupByKey()
                .reduce((value1 , value2) -> value2);
    }

}
