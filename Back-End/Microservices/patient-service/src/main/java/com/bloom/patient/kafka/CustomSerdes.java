package com.bloom.patient.kafka;

import com.bloom.patient.model.Patient;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.jetbrains.annotations.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class CustomSerdes {

    static public final class PatientSerde extends Serdes.WrapperSerde<Patient> {
        public PatientSerde() {
            super(new JsonSerializer<>() , new JsonDeserializer<>(Patient.class));
        }
    }


    @Bean
    @Contract(" -> new")
    public static Serde<Patient> patientSerde() {
        return new CustomSerdes.PatientSerde();
    }

}
