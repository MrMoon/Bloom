package com.bloom.demo.controller.employee;

import com.bloom.demo.model.employee.Days;
import com.bloom.demo.model.employee.Doctor;
import com.bloom.demo.model.employee.DoctorAvailableTimes;
import com.bloom.demo.model.patient.Patient;
import com.bloom.demo.service.employee.DoctorAvailableTimesService;
import com.bloom.demo.service.employee.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/doctor")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorAvailableTimesService doctorAvailableTimesService;

    @GetMapping("/")
    public Flux<Doctor> getAll() {
        return this.doctorService.getAll();
    }

    @GetMapping("/{doctorId}")
    public Mono<Doctor> getDoctorById(@PathVariable("doctorId") String doctorId) {
        return this.doctorService.getDoctorById(doctorId);
    }

    @GetMapping("/patient/{doctorId}")
    public Flux<Patient> getDoctorPatient(@PathVariable("doctorId") String doctorId) {
        return this.doctorService.getDoctorPatients(doctorId);
    }

    @GetMapping("/fee/{doctorId}")
    public Mono<Double> getDoctorFee(@PathVariable("doctorId") String doctorId) {
        return this.doctorService.getDoctorFee(doctorId);
    }

    @GetMapping("/time/{doctorId}")
    public Flux<DoctorAvailableTimes> geDoctorAvailableTimes(@PathVariable("doctorId") String doctorId) {
        return this.doctorService.getDoctorAvailableTimes(doctorId);
    }

    @GetMapping("/days/{doctorId}")
    public Flux<Days> getDoctorFreeDays(@PathVariable("doctorId") String doctorId) {
        return this.doctorService.getDoctorById(doctorId).flatMap(doctor -> {
            ArrayList<Days> days = new ArrayList<>();
            String doctorDays = doctor.getDoctorAvailableDays();
            for (int i = 0; i < 7; ++i) if (doctorDays.charAt(i) == '1') days.add(Days.values()[i]);
            return Mono.just(days);
        }).flatMapIterable(days -> days);
    }

    @PostMapping("/")
    public Mono<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return this.doctorService.createDoctor(doctor);
    }

    @PostMapping("/remove_time")
    public Mono<Void> removeDoctorAvailableTimes(@RequestBody DoctorAvailableTimes doctorAvailableTimes) {
        return this.doctorAvailableTimesService.removeDoctorAvailableDay(doctorAvailableTimes.getTempId().toString());
    }

    @PutMapping("/")
    public Mono<Doctor> updateDoctor(@RequestBody Doctor doctor) {
        return this.doctorService.updateDoctor(doctor);
    }

    @PutMapping("/add_time/")
    public Mono<DoctorAvailableTimes> addDoctorAvailableTimes(@RequestBody DoctorAvailableTimes doctorAvailableTimes) {
        return this.doctorAvailableTimesService.addDoctorAvailableDay(doctorAvailableTimes);
    }

    @DeleteMapping("/{doctorId}")
    public Mono<Void> deleteDoctor(@PathVariable("doctorId") String doctorId) {
        return this.doctorService.deleteDoctorById(doctorId);
    }

}
