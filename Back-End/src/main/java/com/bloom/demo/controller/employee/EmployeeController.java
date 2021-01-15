package com.bloom.demo.controller.employee;

import com.bloom.demo.model.StatNumbers;
import com.bloom.demo.model.StatNumbersString;
import com.bloom.demo.model.employee.Employee;
import com.bloom.demo.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public Flux<Employee> getAll() {
        return this.employeeService.getAll();
    }

    @GetMapping("/freq/salary")
    public Flux<StatNumbers> getSalariesFrequencies() {
        return this.employeeService.getSalariesFrequencies();
    }

    @GetMapping("/freq/jobtype")
    public Flux<StatNumbersString> getJobTypeFrequencies() {
        return this.employeeService.getJobTypeFrequencies();
    }

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
