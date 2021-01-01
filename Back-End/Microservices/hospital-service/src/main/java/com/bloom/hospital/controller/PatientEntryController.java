package com.bloom.hospital.controller;

import com.bloom.hospital.model.PatientEntry;
import com.bloom.hospital.service.PatientEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital/entry")
public class PatientEntryController {

    private final PatientEntryService patientEntryService;

    @GetMapping("/{patientEntryId}")
    public Mono<PatientEntry> getPatientEntryById(@PathVariable("patientEntryId") String patientEntryId) {
        return this.patientEntryService.getPatientEntryById(patientEntryId);
    }

    @PostMapping("/")
    public Mono<PatientEntry> createPatientEntry(@RequestBody Mono<PatientEntry> patientEntryMono) {
        return this.patientEntryService.createPatientEntry(patientEntryMono);
    }

    @PutMapping("/")
    public Mono<PatientEntry> updatePatientEntry(@RequestBody Mono<PatientEntry> patientEntryMono) {
        return this.patientEntryService.updatePatientEntry(patientEntryMono);
    }

    @DeleteMapping("/{patientEntryId}")
    public Mono<Void> deletePatientEntry(@PathVariable("patientEntryId") String patientEntryId) {
        return this.patientEntryService.deletePatientEntryById(patientEntryId);
    }

}
