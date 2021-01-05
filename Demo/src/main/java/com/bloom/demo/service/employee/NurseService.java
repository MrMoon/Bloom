package com.bloom.demo.service.employee;

import com.bloom.demo.model.employee.Nurse;
import reactor.core.publisher.Mono;

public interface NurseService {

    Mono<Nurse> createNurse(Nurse nurse);
    Mono<Nurse> updateNurse(Nurse nurse);
    Mono<Nurse> getNurseById(String nurseId);
    Mono<Void> deleteNurseById(String nurseId);

}
