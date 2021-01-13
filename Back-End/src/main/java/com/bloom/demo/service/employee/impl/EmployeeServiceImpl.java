package com.bloom.demo.service.employee.impl;

import com.bloom.demo.model.employee.Employee;
import com.bloom.demo.model.employee.JobType;
import com.bloom.demo.repository.employee.EmployeeRepository;
import com.bloom.demo.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<Employee> saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee).flatMap(savedEmployee -> {
            savedEmployee.setJobType(this.getEmployeeJobType(savedEmployee.getEmployeeJobType()));
            return Mono.just(savedEmployee);
        });
    }

    @Override
    public Mono<Employee> updateEmployee(Employee employee) {
        return this.employeeRepository.save(employee).flatMap(updatedEmployee -> {
            updatedEmployee.setJobType(this.getEmployeeJobType(updatedEmployee.getEmployeeJobType()));
            return Mono.just(updatedEmployee);
        });
    }

    @Override
    public Mono<Employee> getEmployeeById(String employeeId) {
        return this.employeeRepository.findById(Long.parseLong(employeeId)).map(employee -> {
            employee.setJobType(this.getEmployeeJobType(employee.getEmployeeJobType()));
            return employee;
        });
    }

    @Override
    public Mono<Void> deleteEmployeeById(String employeeId) {
        return this.employeeRepository.deleteById(Long.parseLong(employeeId));
    }

    @Override
    public Flux<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    private JobType getEmployeeJobType(@NotNull String jobType) {
        if (jobType.equalsIgnoreCase("doctor")) return JobType.DOCTOR;
        else if (jobType.equalsIgnoreCase("nurse")) return JobType.NURSE;
        return JobType.EMPLOYEE;
    }
}
