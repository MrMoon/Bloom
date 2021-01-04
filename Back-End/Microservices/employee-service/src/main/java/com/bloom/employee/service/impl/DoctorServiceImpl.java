package com.bloom.employee.service.impl;

import com.bloom.employee.model.Doctor;
import com.bloom.employee.model.JobType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.bloom.employee.service.DoctorService;
import com.bloom.employee.repository.DoctorRepository;

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
