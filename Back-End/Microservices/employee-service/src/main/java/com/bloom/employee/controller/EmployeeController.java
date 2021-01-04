package com.bloom.employee.controller;

import com.bloom.employee.model.Employee;
import com.bloom.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public Mono<Employee> getEmployeeById(@PathVariable("employeeId") String employeeId) {
        return this.employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/")
    public Mono<Employee> createEmployee(@RequestBody Employee employee) {
        return this.employeeService.saveEmployee(employee);
    }

    @PutMapping("/")
    public Mono<Employee> updateEmployee(@RequestBody Employee employee) {
        return this.employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public Mono<Void> deleteEmployee(@PathVariable("employeeId") String employeeId) {
        return this.employeeService.deleteEmployeeById(employeeId);
    }

}
