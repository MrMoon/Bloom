package com.bloom.employee.service;

import com.bloom.employee.model.Nurse;
import reactor.core.publisher.Mono;

public interface NurseService {

    Mono<Nurse> createNurse(Nurse nurse);
    Mono<Nurse> updateNurse(Nurse nurse);
    Mono<Nurse> getNurseById(Long nurseId);
    Mono<Void> deleteNurseById(Long nurseId);

}