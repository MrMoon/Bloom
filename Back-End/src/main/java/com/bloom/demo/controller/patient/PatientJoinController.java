package com.bloom.demo.controller.patient;

import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.patient.PatientRoomDetails;
import com.bloom.demo.service.patient.PatientJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/patient")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class PatientJoinController {

    private final PatientJoinService patientJoinService;


    @GetMapping("/room/{patientId}")
    public Mono<PatientRoomDetails> getPatientRoomDetails(@PathVariable("patientId") String patientId) {
        return this.patientJoinService.getPatientRoomDetails(patientId);
    }


    @GetMapping("/doctor/{patientId}")
    public Mono<Doctor> getPatientDoctor(@PathVariable("patientId") String patientId) {
        return this.patientJoinService.getPatientDoctor(patientId);
    }

}
