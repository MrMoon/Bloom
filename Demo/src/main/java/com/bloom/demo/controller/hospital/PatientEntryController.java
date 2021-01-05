package com.bloom.demo.controller.hospital;

import com.bloom.demo.model.hospital.PatientEntry;
import com.bloom.demo.service.hospital.PatientEntryService;
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
