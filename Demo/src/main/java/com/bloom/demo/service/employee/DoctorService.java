package com.bloom.demo.service.employee;

import com.bloom.demo.model.employee.Doctor;
import reactor.core.publisher.Mono;

public interface DoctorService {

    Mono<Doctor> createDoctor(Doctor doctor);
    Mono<Doctor> getDoctorById(String doctorId);
    Mono<Doctor> updateDoctor(Doctor doctor);
    Mono<Void> deleteDoctorById(String doctorId);

}
