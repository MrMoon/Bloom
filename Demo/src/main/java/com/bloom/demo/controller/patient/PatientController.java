package com.bloom.demo.controller.patient;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.model.patient.PatientRoomDetails;
import com.bloom.demo.service.patient.PatientService;
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

    @GetMapping("/room/{patientId}")
    public Mono<PatientRoomDetails> getPatientRoomDetails(@PathVariable("patientId") String patientId) {
        return this.patientService.getPatientRoomDetails(patientId);
    }

    @GetMapping("/doctor/{patientId}")
    public Mono<Doctor> getPatientDoctor(@PathVariable("patientId") String patientId) {
        return this.patientService.getPatientDoctor(patientId);
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
