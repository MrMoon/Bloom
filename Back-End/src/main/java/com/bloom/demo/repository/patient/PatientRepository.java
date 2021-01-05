package com.bloom.demo.repository.patient;

import com.bloom.demo.model.patient.Patient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PatientRepository extends ReactiveCrudRepository<Patient, Long> {

    /*@Modifying
    @Query("SELECT * FROM patient WHERE patient_assigned_doctor_id = :assignedDoctorId")*/
    Flux<Patient> findAllByPatientAssignedDoctorId(Long assignedDoctorId);

}
