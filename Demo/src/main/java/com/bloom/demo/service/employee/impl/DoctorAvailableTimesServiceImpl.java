package com.bloom.demo.service.employee.impl;

import com.bloom.demo.model.employee.DoctorAvailableTimes;
import com.bloom.demo.repository.employee.DoctorAvailableTimesRepository;
import com.bloom.demo.service.employee.DoctorAvailableTimesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DoctorAvailableTimesServiceImpl implements DoctorAvailableTimesService {

    private final DoctorAvailableTimesRepository doctorAvailableTimesRepository;

    @Override
    public Mono<DoctorAvailableTimes> addDoctorAvailableDay(DoctorAvailableTimes doctorAvailableTimes) {
        return this.doctorAvailableTimesRepository.save(doctorAvailableTimes);
    }

    @Override
    public Mono<Void> removeDoctorAvailableDay(String tempId) {
        return this.doctorAvailableTimesRepository.deleteById(Long.parseLong(tempId));
    }
}
