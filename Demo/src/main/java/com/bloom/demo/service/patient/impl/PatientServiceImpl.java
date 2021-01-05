package com.bloom.demo.service.patient.impl;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.model.patient.PatientRoomDetails;
import com.bloom.demo.repository.patient.PatientRepository;
import com.bloom.demo.service.employee.DoctorService;
import com.bloom.demo.service.hospital.RoomService;
import com.bloom.demo.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final RoomService roomService;
    private final DoctorService doctorService;

    @Override
    public Mono<Patient> createPatient(Patient patient) {
        return this.patientRepository.save(patient).flatMap(savedPatient -> {
            savedPatient.setPatientAge(this.getAge(savedPatient.getPatientDateOfBirth()));
            return Mono.just(savedPatient);
        });
    }

    @Override
    public Mono<Patient> updatePatient(Patient patient) {
        return this.patientRepository.save(patient).flatMap(savedPatient -> {
            savedPatient.setPatientAge(this.getAge(savedPatient.getPatientDateOfBirth()));
            return Mono.just(savedPatient);
        });
    }

    @Override
    public Mono<Patient> getPatientById(String patientId) {
        return this.patientRepository.findById(Long.parseLong(patientId)).flatMap(patient -> {
            patient.setPatientAge(this.getAge(patient.getPatientDateOfBirth()));
            return Mono.just(patient);
        });
    }

    @Override
    public Mono<PatientRoomDetails> getPatientRoomDetails(String patientId) {
        return this.getPatientById(patientId).flatMap(patient -> {
            PatientRoomDetails patientRoomDetails = new PatientRoomDetails();
            patientRoomDetails.setPatientRoomNumber(patient.getPatientRoomNumber());
            patientRoomDetails.setGender(patient.getGender());
            patientRoomDetails.setPatientName(patient.getPatientName());
            return this.roomService.getRoomByNumber(patient.getPatientRoomNumber().toString()).flatMap(room -> {
                patientRoomDetails.setPatientRoomType(room.getRoomType());
                return this.doctorService.getDoctorById(patient.getPatientAssignedDoctorId().toString()).flatMap(doctor -> {
                    patientRoomDetails.setPatientAssignedDoctorName(doctor.getEmployeeName());
                    return Mono.just(patientRoomDetails);
                });
            });
        });
    }

    @Override
    public Mono<Doctor> getPatientDoctor(String patientId) {
        return this.getPatientById(patientId).flatMap(patient -> this.doctorService.getDoctorById(patient.getPatientAssignedDoctorId().toString()));
    }

    @Override
    public Mono<Void> deletePatientById(String patientId) {
        return this.patientRepository.deleteById(Long.parseLong(patientId));
    }

    @Override
    public Flux<Patient> getDoctorPatients(String doctorId) {
        return this.patientRepository.findAllByPatientAssignedDoctorId(Long.parseLong(doctorId));
    }

    private int getAge(LocalDate birthDate) {
        return Period.between(birthDate , LocalDate.now()).getYears();
    }
}
