package com.bloom.demo.service.patient;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.model.patient.PatientRoomDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientJoinService {

    Mono<PatientRoomDetails> getPatientRoomDetails(String patientId);

    Mono<Doctor> getPatientDoctor(String patientId);

    Flux<Patient> getDoctorPatients(String doctorId);

}
