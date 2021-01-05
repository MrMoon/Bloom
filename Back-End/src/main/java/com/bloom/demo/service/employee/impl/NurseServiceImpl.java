package com.bloom.demo.service.employee.impl;

import com.bloom.demo.model.employee.JobType;
import com.bloom.demo.model.employee.Nurse;
import com.bloom.demo.model.hospital.Inventory;
import com.bloom.demo.repository.employee.NurseRepository;
import com.bloom.demo.repository.hospital.InventoryRepository;
import com.bloom.demo.service.employee.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {

    private final NurseRepository nurseRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public Mono<Nurse> createNurse(Nurse nurse) {
        nurse.setJobType(JobType.NURSE);
        nurse.setEmployeeJobType(nurse.getJobType().name());
        return this.nurseRepository.save(nurse);
    }

    @Override
    public Mono<Nurse> updateNurse(Nurse nurse) {
        nurse.setJobType(JobType.NURSE);
        nurse.setEmployeeJobType(nurse.getJobType().name());
        return this.nurseRepository.save(nurse);
    }

    @Override
    public Mono<Nurse> getNurseById(String nurseId) {
        return this.nurseRepository.findById(Long.parseLong(nurseId)).flatMap(nurse -> {
            nurse.setJobType(JobType.NURSE);
            return Mono.just(nurse);
        });
    }

    @Override
    public Mono<Void> deleteNurseById(String nurseId) {
        return this.nurseRepository.deleteById(Long.parseLong(nurseId));
    }

    @Override
    public Flux<Inventory> getNurseInventories(String nurseId) {
        return this.inventoryRepository.findAllByInventoryMangedBy(Long.parseLong(nurseId));
    }
}
