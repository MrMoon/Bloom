package com.bloom.demo.service.employee;

import com.bloom.demo.model.employee.DoctorAvailableTimes;
import reactor.core.publisher.Mono;

public interface DoctorAvailableTimesService {

    Mono<DoctorAvailableTimes> addDoctorAvailableDay(DoctorAvailableTimes doctorAvailableTimes);

    Mono<Void> removeDoctorAvailableDay(String tempId);

}
