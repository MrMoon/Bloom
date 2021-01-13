package com.bloom.demo.service.hospital.impl;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.hospital.PatientEntry;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.model.patient.PatientRoomDetails;
import com.bloom.demo.repository.employee.DoctorRepository;
import com.bloom.demo.repository.hospital.PatientEntryRepository;
import com.bloom.demo.repository.hospital.RoomRepository;
import com.bloom.demo.repository.patient.PatientRepository;
import com.bloom.demo.service.hospital.PatientEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientEntryServiceImpl implements PatientEntryService {

    private final PatientEntryRepository patientEntryRepository;
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public Mono<PatientEntry> createPatientEntry(PatientEntry patientEntry) {
        return this
                .patientEntryRepository
                .save(patientEntry);
    }

    @Override
    public Mono<PatientEntry> getPatientEntryById(String patientEntryId) {
        return this
                .patientEntryRepository
                .findById(Long.parseLong(patientEntryId));
    }

    @Override
    public Mono<PatientEntry> updatePatientEntry(PatientEntry patientEntry) {
        return this
                .patientEntryRepository
                .save(patientEntry);
    }

    @Override
    public Mono<Void> deletePatientEntryById(String patientEntryId) {
        return this
                .patientEntryRepository
                .deleteById(Long.parseLong(patientEntryId));
    }

    @Override
    public Mono<Patient> getPatientDetailsById(String patientEntryId) {
        return this
                .getPatientEntryById(patientEntryId)
                .flatMap(patientEntry -> this.patientRepository.findById(patientEntry.getPatientId()));
    }

    @Override
    public Mono<Doctor> getPatientDoctor(String patientEntryId) {
        return this.patientEntryRepository
                .findById(Long.valueOf(patientEntryId))
                .flatMap(patientEntry -> this.doctorRepository
                        .findById(patientEntry.getPatientDoctorId())
                        .flatMap(Mono::just));
    }

    @Override
    public Flux<Patient> getDoctorPatients(String doctorId) {
        return this
                .patientEntryRepository
                .findPatientEntriesByPatientDoctorId(Long.parseLong(doctorId))
                .flatMap(patientEntry -> this.patientRepository.findById(patientEntry.getPatientId()));
    }

    @Override
    public Mono<PatientRoomDetails> getPatientRoomDetails(String patientId) {
        return this
                .patientEntryRepository
                .findPatientEntryByPatientId(Long.parseLong(patientId))
                .flatMap(patientEntry -> this.patientRepository
                        .findById(patientEntry.getPatientId())
                        .flatMap(patient -> this.doctorRepository
                                .findById(patientEntry.getPatientDoctorId())
                                .flatMap(doctor -> this.roomRepository
                                        .findById(patientEntry.getPatientRoomNumber())
                                        .flatMap(room -> {
                                            PatientRoomDetails patientRoomDetails = new PatientRoomDetails();
                                            patientRoomDetails.setPatientRoomNumber(room.getRoomId());
                                            patientRoomDetails.setPatientRoomType(room.getRoomType());
                                            patientRoomDetails.setGender(patient.getGender());
                                            patientRoomDetails.setPatientName(patient.getPatientName());
                                            patientRoomDetails.setPatientAssignedDoctorName(doctor.getEmployeeName());
                                            return Mono.just(patientRoomDetails);
                                        }))));
    }

    @Override
    public Flux<PatientEntry> getAll() {
        return this.patientEntryRepository.findAll();
    }
}
