package com.bloom.demo.repository.employee;

import com.bloom.demo.model.employee.DoctorAvailableTimes;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface DoctorAvailableTimesRepository extends ReactiveCrudRepository<DoctorAvailableTimes, Long> {

    Flux<DoctorAvailableTimes> findAllByDoctorId(Long doctorId);

}
