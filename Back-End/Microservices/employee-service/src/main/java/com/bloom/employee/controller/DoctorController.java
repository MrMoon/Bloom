package com.bloom.employee.controller;

import com.bloom.employee.model.Doctor;
import com.bloom.employee.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{doctorId}")
    public Mono<Doctor> getDoctorById(@PathVariable("doctorId") String doctorId) {
        return this.doctorService.getDoctorById(doctorId);
    }

    @PostMapping("/")
    public Mono<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return this.doctorService.createDoctor(doctor);
    }

    @PutMapping("/")
    public Mono<Doctor> updateDoctor(@RequestBody Doctor doctor){
        return this.doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/{doctorId}")
    public Mono<Void> deleteDoctor(@PathVariable("doctorId") String doctorId) {
        return this.doctorService.deleteDoctorById(doctorId);
    }

}
