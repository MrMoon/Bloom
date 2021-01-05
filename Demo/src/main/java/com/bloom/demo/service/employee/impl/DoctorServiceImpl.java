package com.bloom.demo.service.employee.impl;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.employee.DoctorAvailableTimes;
import com.bloom.demo.model.employee.JobType;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.repository.employee.DoctorAvailableTimesRepository;
import com.bloom.demo.repository.employee.DoctorRepository;
import com.bloom.demo.service.employee.DoctorService;
import com.bloom.demo.service.hospital.FeeService;
import com.bloom.demo.service.patient.PatientJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorAvailableTimesRepository doctorAvailableTimesRepository;
    private final PatientJoinService patientJoinService;
    private final FeeService feeService;

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

    @Override
    public Flux<Patient> getDoctorPatients(String doctorId) {
        return this.patientJoinService.getDoctorPatients(doctorId);
    }

    @Override
    public Mono<Double> getDoctorFee(String doctorId) {
        return this.patientJoinService
                .getDoctorPatients(doctorId)
                .flatMap(patient -> this.feeService.getPatientFinalPrice(patient.getPatientId().toString()))
                .reduce(0.0 , Double::sum);
    }

    @Override
    public Flux<DoctorAvailableTimes> getDoctorAvailableTimes(String doctorId) {
        return this.doctorAvailableTimesRepository
                .findAllByDoctorId(Long.parseLong(doctorId))
                .distinct();
    }
}
