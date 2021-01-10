package com.bloom.demo.controller.employee;

import com.bloom.demo.model.employee.Nurse;
import com.bloom.demo.model.hospital.Inventory;
import com.bloom.demo.service.employee.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/employee/nurse")
public class NurseController {

    private final NurseService nurseService;

    @GetMapping("/{nurseId}")
    public Mono<Nurse> getNurseById(@PathVariable("nurseId") String nurseId) {
        return this.nurseService.getNurseById(nurseId);
    }

    @GetMapping("/inventories/{nurseId}")
    public Flux<Inventory> getNurseInventories(@PathVariable("nurseId") String nurseId) {
        return this.nurseService.getNurseInventories(nurseId);
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
        return this.nurseService.deleteNurseById(nurseId);
    }

}
