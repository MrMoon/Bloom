package com.bloom.employee.service.impl;

import com.bloom.employee.model.JobType;
import com.bloom.employee.model.Nurse;
import com.bloom.employee.repository.NurseRepository;
import com.bloom.employee.service.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {

    private final NurseRepository nurseRepository;

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
}
