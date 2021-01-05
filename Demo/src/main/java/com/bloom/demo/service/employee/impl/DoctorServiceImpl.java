package com.bloom.demo.service.employee.impl;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.employee.JobType;
import com.bloom.demo.repository.employee.DoctorRepository;
import com.bloom.demo.service.employee.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Mono<Doctor> createDoctor(Doctor doctor) {
        doctor.setJobType(JobType.DOCTOR);
        doctor.setEmployeeJobType(doctor.getJobType().name());
        return this.doctorRepository.save(doctor);
    }

    @Override
    public Mono<Doctor> getDoctorById(String doctorId) {
        return this.doctorRepository.findById(Long.parseLong(doctorId)).flatMap(doctor -> {
            doctor.setJobType(JobType.DOCTOR);
            return Mono.just(doctor);
        });
    }

    @Override
    public Mono<Doctor> updateDoctor(Doctor doctor) {
        doctor.setJobType(JobType.DOCTOR);
        doctor.setEmployeeJobType(doctor.getJobType().name());
        return this.doctorRepository.save(doctor);
    }

    @Override
    public Mono<Void> deleteDoctorById(String doctorId) {
        return this.doctorRepository.deleteById(Long.parseLong(doctorId));
    }
}
