package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.Fee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface FeeRepository extends ReactiveCrudRepository<Fee, Long> {


    /*@Modifying
    @Query("SELECT * FROM fee WHERE patientId = :patientId")*/
    Flux<Fee> findAllByPatientId(Long patientId);

    Flux<Fee> findFeesByFeeDateAfter(LocalDate localDate);

}
