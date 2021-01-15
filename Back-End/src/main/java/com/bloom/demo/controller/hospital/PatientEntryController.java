package com.bloom.demo.controller.hospital;

import com.bloom.demo.model.hospital.PatientEntry;
import com.bloom.demo.model.hospital.PatientEntryType;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.service.hospital.PatientEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hospital/entry")
public class PatientEntryController {

    private final PatientEntryService patientEntryService;

    @GetMapping("/")
    public Flux<PatientEntry> getAll() {
        return this.patientEntryService.getAll();
    }

    @GetMapping("/{patientEntryId}")
    public Mono<PatientEntry> getPatientEntryById(@PathVariable("patientEntryId") String patientEntryId) {
        return this.patientEntryService.getPatientEntryById(patientEntryId);
    }

    @GetMapping("/number/type/{patientEntryType}")
    public Mono<Long> getNumberOfEntriesByType(@PathVariable("patientEntryType") PatientEntryType patientEntryType) {
        return this.patientEntryService.getNumberOfEntriesType(patientEntryType);
    }

    @GetMapping("/patient/{patientEntryId}")
    public Mono<Patient> getPatientByEntryId(@PathVariable("patientEntryId") String patientEntryId) {
        return this.patientEntryService.getPatientDetailsById(patientEntryId);
    }

    @PostMapping("/")
    public Mono<PatientEntry> createPatientEntry(@RequestBody Mono<PatientEntry> patientEntryMono) {
        return patientEntryMono.flatMap(this.patientEntryService::createPatientEntry);
    }

    @PutMapping("/")
    public Mono<PatientEntry> updatePatientEntry(@RequestBody Mono<PatientEntry> patientEntryMono) {
        return patientEntryMono.flatMap(this.patientEntryService::updatePatientEntry);
    }

    @DeleteMapping("/{patientEntryId}")
    public Mono<Void> deletePatientEntry(@PathVariable("patientEntryId") String patientEntryId) {
        return this.patientEntryService.deletePatientEntryById(patientEntryId);
    }

}
