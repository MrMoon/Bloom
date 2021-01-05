package com.bloom.demo.repository.hospital;

import com.bloom.demo.model.hospital.Fee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FeeRepository extends ReactiveCrudRepository<Fee, Long> {

}
