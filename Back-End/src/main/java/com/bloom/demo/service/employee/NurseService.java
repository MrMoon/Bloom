package com.bloom.demo.service.employee;

import com.bloom.demo.model.employee.Nurse;
import com.bloom.demo.model.hospital.Inventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NurseService {

    Mono<Nurse> createNurse(Nurse nurse);

    Mono<Nurse> updateNurse(Nurse nurse);

    Mono<Nurse> getNurseById(String nurseId);

    Mono<Void> deleteNurseById(String nurseId);

    Flux<Inventory> getNurseInventories(String nurseId);

}
