package com.bloom.demo.service.hospital;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.hospital.PatientEntry;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.model.patient.PatientRoomDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientEntryService {

    Mono<PatientEntry> createPatientEntry(PatientEntry patientEntry);

    Mono<PatientEntry> getPatientEntryById(String patientEntryId);

    Mono<PatientEntry> updatePatientEntry(PatientEntry patientEntry);

    Mono<Void> deletePatientEntryById(String patientEntryId);

    Mono<Patient> getPatientDetailsById(String patientEntryId);

    Mono<Doctor> getPatientDoctor(String patientId);

    Flux<Patient> getDoctorPatients(String doctorId);

    Mono<PatientRoomDetails> getPatientRoomDetails(String patientId);

    Flux<PatientEntry> getAll();
}
