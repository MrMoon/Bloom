package com.bloom.employee.service;

import com.bloom.employee.model.Doctor;
import reactor.core.publisher.Mono;

public interface DoctorService {

    Mono<Doctor> createDoctor(Doctor doctor);
    Mono<Doctor> getDoctorById(Long doctorId);
    Mono<Doctor> updateDoctor(Doctor doctor);
    Mono<Void> deleteDoctorById(Long doctorId);

}
