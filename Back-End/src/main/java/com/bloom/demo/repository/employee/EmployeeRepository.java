package com.bloom.demo.repository.employee;

import com.bloom.demo.model.StatNumbers;
import com.bloom.demo.model.StatNumbersString;
import com.bloom.demo.model.employee.Employee;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {

    @Query("SELECT employee_salary AS total, COUNT(*) AS x FROM employee GROUP BY employee_salary;")
    Flux<StatNumbers> findEmployeeSalariesFrequencies();

    @Query("SELECT employee_job_type AS name, COUNT(*) AS freq FROM employee GROUP BY employee_job_type;")
    Flux<StatNumbersString> findEmployeeJobTypeFrequencies();
}
