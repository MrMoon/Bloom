package com.bloom.demo.service.patient.impl;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.model.patient.PatientRoomDetails;
import com.bloom.demo.repository.employee.DoctorRepository;
import com.bloom.demo.repository.hospital.RoomRepository;
import com.bloom.demo.repository.patient.PatientRepository;
import com.bloom.demo.service.patient.PatientJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientJoinServiceImpl implements PatientJoinService {

    private final RoomRepository roomRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public Mono<PatientRoomDetails> getPatientRoomDetails(String patientId) {
        return this.patientRepository.findById(Long.parseLong(patientId)).flatMap(patient -> {
            PatientRoomDetails patientRoomDetails = new PatientRoomDetails();
            patientRoomDetails.setPatientRoomNumber(patient.getPatientRoomNumber());
            patientRoomDetails.setGender(patient.getGender());
            patientRoomDetails.setPatientName(patient.getPatientName());
            return this.roomRepository.findById(patient.getPatientRoomNumber()).flatMap(room -> {
                patientRoomDetails.setPatientRoomType(room.getRoomType());
                return this.doctorRepository.findById(Long.parseLong(patient.getPatientAssignedDoctorId().toString())).flatMap(doctor -> {
                    patientRoomDetails.setPatientAssignedDoctorName(doctor.getEmployeeName());
                    return Mono.just(patientRoomDetails);
                });
            });
        });
    }

    @Override
    public Mono<Doctor> getPatientDoctor(String patientId) {
        return this.patientRepository
                .findById(Long.parseLong(patientId))
                .flatMap(patient -> this.doctorRepository.findById(patient.getPatientAssignedDoctorId()));
    }

    @Override
    public Flux<Patient> getDoctorPatients(String doctorId) {
        return this.patientRepository.findAllByPatientAssignedDoctorId(Long.parseLong(doctorId));
    }
}
