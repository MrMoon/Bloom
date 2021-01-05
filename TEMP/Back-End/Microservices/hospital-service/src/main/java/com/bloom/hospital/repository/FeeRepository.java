package com.bloom.hospital.repository;

import com.bloom.hospital.model.Fee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FeeRepository extends ReactiveCrudRepository<Fee, Long> {

}
