package com.bloom.employee.service.impl;

import com.bloom.employee.model.Employee;
import com.bloom.employee.model.JobType;
import com.bloom.employee.repository.EmployeeRepository;
import com.bloom.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<Employee> saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee).map(savedEmployee -> {
            savedEmployee.setJobType(this.getEmployeeJobType(savedEmployee.getEmployeeJobType()));
            return savedEmployee;
        });
    }

    @Override
    public Mono<Employee> updateEmployee(Employee employee) {
        return this.employeeRepository.save(employee).map(updatedEmployee -> {
            updatedEmployee.setJobType(this.getEmployeeJobType(updatedEmployee.getEmployeeJobType()));
            return updatedEmployee;
        });
    }

    @Override
    public Mono<Employee> getEmployeeById(Long employeeId) {
        return this.employeeRepository.findById(employeeId).map(employee -> {
            employee.setJobType(this.getEmployeeJobType(employee.getEmployeeJobType()));
            return employee;
        });
    }

    @Override
    public Mono<Void> deleteEmployeeById(Long employeeId) {
        return this.employeeRepository.deleteById(employeeId);
    }

    private JobType getEmployeeJobType(@NotNull String jobType) {
        if(jobType.equalsIgnoreCase("doctor")) return JobType.DOCTOR;
        else if(jobType.equalsIgnoreCase("nurse")) return JobType.NURSE;
        return JobType.EMPLOYEE;
    }
}
