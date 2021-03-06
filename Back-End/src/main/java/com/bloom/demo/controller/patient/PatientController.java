package com.bloom.demo.controller.patient;

import com.bloom.demo.model.StatNumbers;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/")
    public Flux<Patient> getAllPatients() {
        return this.patientService.getAll();
    }

    @GetMapping("/numbers")
    public Mono<StatNumbers> getPatientNumbersOfAdmitted() {
        return this.patientService.getNumbersAdmitted();
    }

    @GetMapping("/gender")
    public Mono<StatNumbers> getPatientNumbersGender() {
        return this.patientService.getNumbersGender();
    }

    @GetMapping("/teens")
    public Mono<StatNumbers> getPatientNumbersTeens() {
        return this.patientService.getNumberOfTeens();
    }

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
