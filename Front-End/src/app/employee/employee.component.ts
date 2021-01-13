import {Component, OnInit} from '@angular/core';
import {Employee} from '../model/Employee';
import {EmployeeService} from '../employee.service';
import {ToastrService} from 'ngx-toastr';
import {Doctor} from '../model/Doctor';
import {Nurse} from '../model/Nurse';
import {DoctorService} from '../doctor.service';
import {NurseService} from '../nurse.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  doctor: Doctor = new Doctor();
  nurse: Nurse = new Nurse();
  doctorDaysOptions = ['SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'];
  nurseRanks = ['ONE', 'TWO', 'THREE', 'FOUR', 'FIVE'];
  nurseShifts = ['A', 'B', 'C'];
  firstName = '';
  lastName = '';
  isDoctor = false;
  isNurse = false;
  flag = false;
  doctorSelectedDays: Array<string>;

  constructor(private doctorService: DoctorService, private nurseService: NurseService,
              private employeeService: EmployeeService, private toast: ToastrService) {
  }

  ngOnInit(): void {
  }

  onJobTypeChange = () => {
    const job = this.employee.employeeJobType.toUpperCase();
    this.isDoctor = job === 'DOCTOR';
    this.isNurse = job === 'NURSE';
  }

  replaceAt = (temp: string, index: number, replacement) => temp.substr(0, index) + replacement + temp.substr(index + replacement.length);

  onSubmitEmployee = () => {
    this.toast.clear();
    this.employee.employeeName = this.firstName + ' ' + this.lastName;
    if (this.isDoctor) {
      this.doctor.jobType = 'Doctor'.toUpperCase();
      this.doctor.employeeId = this.employee.employeeId;
      this.doctor.jobType = this.employee.jobType;
      this.doctor.employeeName = this.employee.employeeName;
      this.doctor.employeeDateOfBirth = this.employee.employeeDateOfBirth;
      this.doctor.employeeSalary = this.employee.employeeSalary;
      let temp = '0000000';
      this.doctorSelectedDays.forEach(selectedDay => {
        const index = this.doctorDaysOptions.findIndex(day => day === selectedDay);
        if (index !== undefined) temp = this.replaceAt(temp, index, '1');
      })
      this.doctor.doctorAvailableDays = temp;
      this.doctor.doctorAvailableTimes = null;
      if (this.doctor.employeeId !== undefined) {
        this.doctorService.updateDoctor(this.doctor).subscribe(updatedDoctor => {
          this.doctor = updatedDoctor;
          this.toast.success('Doctor Updated Successfully', 'Doctor ' + this.employee.employeeId + ' Status');
        }, error => this.toast.error('Doctor Update Failed\n' + error, 'Doctor Update Status'));
      } else {
        this.doctorService.createDoctor(this.doctor).subscribe(savedDoctor => {
          this.doctor = savedDoctor;
          this.toast.success('Doctor Created Successfully', 'Doctor ' + this.employee.employeeId + ' Status');
        }, error => this.toast.error('Doctor Creation Failed\n' + error, 'Doctor Creation Status'));
      }
    } else if (this.isNurse) {
      this.nurse.jobType = 'Nurse'.toUpperCase();
      this.nurse.employeeId = this.employee.employeeId;
      this.nurse.jobType = this.employee.jobType;
      this.nurse.employeeName = this.employee.employeeName;
      this.nurse.employeeDateOfBirth = this.employee.employeeDateOfBirth;
      this.nurse.employeeSalary = this.employee.employeeSalary;
      if (this.nurse.employeeId !== undefined) {
        this.nurseService.updateNurse(this.nurse).subscribe(updatedNurse => {
          this.nurse = updatedNurse;
          this.toast.success('Nurse Updated Successfully', 'Nurse ' + this.employee.employeeId + ' Status');
        }, error => this.toast.error('Nurse Update Failed\n' + error.value, 'Nurse Update Status'))
      } else {
        this.nurseService.createNurse(this.nurse).subscribe(savedNurse => {
          this.nurse = savedNurse;
          this.toast.success('Nurse Created Successfully', 'Nurse ' + this.employee.employeeId + ' Status');
        }, error => this.toast.error('Nurse Creation Failed\n' + error.value, 'Nurse Creation Status'));
      }
    } else {
      this.employee.jobType = 'Employee'.toUpperCase();
      this.employee.employeeName = this.firstName + ' ' + this.lastName;
      if (this.employee.employeeId !== undefined) {
        this.employeeService.updateEmployee(this.employee).subscribe(updatedEmployee => {
          this.employee = updatedEmployee;
          this.toast.success('Employee Updated Successfully', 'Employee ' + this.employee.employeeId + ' Status');
        }, error => this.toast.error('Employee Update Failed\n' + error.value, 'Employee Update Status'));
      } else {
        this.employeeService.createEmployee(this.employee).subscribe(savedEmployee => {
          this.employee = savedEmployee;
          this.toast.success('Employee Created Successfully', 'Employee ' + this.employee.employeeId + ' Status');
        }, error => this.toast.error('Employee Creation Failed\n' + error.value, 'Employee Creation Status'));
      }
    }
  }

  clearSalary = () => {
    if (!this.flag) {
      this.employee.employeeSalary = 0.0;
      this.flag = true;
    }
  }
}
