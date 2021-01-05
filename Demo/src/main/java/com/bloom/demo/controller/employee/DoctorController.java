package com.bloom.demo.controller.employee;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.service.employee.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
