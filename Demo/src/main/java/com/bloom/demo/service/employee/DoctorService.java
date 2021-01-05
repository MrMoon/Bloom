package com.bloom.demo.service.employee;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.employee.DoctorAvailableTimes;
import com.bloom.demo.model.patient.Patient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DoctorService {

    Mono<Doctor> createDoctor(Doctor doctor);

    Mono<Doctor> getDoctorById(String doctorId);

    Mono<Doctor> updateDoctor(Doctor doctor);

    Mono<Void> deleteDoctorById(String doctorId);

    Flux<Patient> getDoctorPatients(String doctorId);

    Mono<Double> getDoctorFee(String doctorId);

    Flux<DoctorAvailableTimes> getDoctorAvailableTimes(String doctorId);

}
