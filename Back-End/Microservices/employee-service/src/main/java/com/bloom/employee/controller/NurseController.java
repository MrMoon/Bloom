package com.bloom.employee.controller;

import com.bloom.employee.model.Nurse;
import com.bloom.employee.service.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nurse")
public class NurseController {

    private final NurseService nurseService;

    @GetMapping("/{nurseId}")
    public Mono<Nurse> getNurseById(@PathVariable("nurseId") String nurseId) {
        return this.nurseService.getNurseById(Long.parseLong(nurseId));
    }

    @PostMapping("/")
    public Mono<Nurse> createNurse(@RequestBody Nurse nurse) {
        return this.nurseService.createNurse(nurse);
    }

    @PutMapping("/")
    public Mono<Nurse> updateNurse(@RequestBody Nurse nurse) {
        return this.nurseService.updateNurse(nurse);
    }

    @DeleteMapping("/{nurseId}")
    public Mono<Void> deleteNurse(@PathVariable("nurseId") String nurseId) {
        return this.nurseService.deleteNurseById(Long.parseLong(nurseId));
    }

}
