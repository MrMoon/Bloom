package com.bloom.patient.controller;

import com.bloom.patient.model.Patient;
import com.bloom.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{patientId}")
    public Mono<Patient> getPatientById(@PathVariable("patientId") String patientId) {
        return this.patientService.getPatientById(patientId);
    }

    @PostMapping("/")
    public Mono<Patient> createPatient(@RequestBody Patient patient) {
        return this.patientService.createPatient(patient);
    }

    @PutMapping("/")
    public Mono<Patient> updatePatient(@RequestBody Patient patient) {
        return this.patientService.updatePatient(patient);
    }

    @DeleteMapping("/{patientId}")
    public Mono<Void> deletePatient(@PathVariable("patientId") String patientId) {
        return this.patientService.deletePatientById(patientId);
    }
}
