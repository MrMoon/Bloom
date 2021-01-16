import {Component, OnInit} from '@angular/core';
import {Doctor} from '../model/Doctor';
import {ToastrService} from 'ngx-toastr';
import {DoctorService} from '../doctor.service';
import {DoctorAvailableTimes} from '../model/DoctorAvailableTimes';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {

  doctorId: string;
  doctorFlag: boolean;
  doctorTimeTemp: string;
  doctor: Doctor;
  doctorSelectedDays: Array<string>;
  doctorTimes: Array<DoctorAvailableTimes> = new Array<DoctorAvailableTimes>();

  doctorDaysOptions = ['SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'];

  constructor(private doctorService: DoctorService, private toast: ToastrService) {
  }

  ngOnInit(): void {
  }

  replaceAt = (temp: string, index: number, replacement) => temp.substr(0, index) + replacement + temp.substr(index + replacement.length);


  getDoctorDetails() {
    this.toast.clear();
    if (this.doctorId === null || this.doctorId === undefined) this.toast.error('Please Enter a valid ID', 'Doctor ID');
    else {
      this.doctorService.getDoctorById(this.doctorId).subscribe(value => {
        if (value === null || value === undefined) {
          this.toast.error('Doctor ' + this.doctorId + ' Does not exists', 'Doctor Status');
          this.doctorFlag = false;
        } else {
          this.doctor = value;
          this.doctorSelectedDays = new Array<string>();
          const doctorDays = this.doctor.doctorAvailableDays;
          for (let i = 0; i < 7; ++i) if (doctorDays[i] === '1') this.doctorSelectedDays.push(this.doctorDaysOptions[i]);
          this.doctorFlag = true;
          this.doctorService.getAvailableTimes(this.doctor.employeeId).subscribe(doctorTimes => this.doctorTimes = doctorTimes);
        }
      });
    }
  }

  updateDoctor() {
    this.toast.clear();
    if (this.doctor === null || this.doctor === undefined) this.toast.error('Please Enter a valid ID', 'Doctor ID');
    else {
      let tempDoctorDays = '0000000';
      this.doctorSelectedDays.forEach(selectedDay => {
        const index = this.doctorDaysOptions.findIndex(day => day === selectedDay);
        if (index !== undefined) tempDoctorDays = this.replaceAt(tempDoctorDays, index, '1');
      })
      this.doctor.doctorAvailableDays = tempDoctorDays;
      this.doctorService.updateDoctor(this.doctor).subscribe(value => {
        this.doctor = value;
        this.toast.success('Doctor ' + this.doctor.employeeId + ' Updated Successfully', 'Doctor Update Status');
      }, error => this.toast.error('Doctor ' + this.doctor.employeeId + ' Updated Failed' + error, 'Doctor Update Status'));
    }
  }

  deleteDoctor() {
    this.toast.clear();
    if (this.doctor === null || this.doctor === undefined) this.toast.error('Please Enter a valid ID', 'Doctor ID');
    else {
      this.doctorService.deleteDoctor(this.doctor.employeeId).subscribe(value => {
        if (value.status === 200) {
          this.doctorFlag = false;
          this.toast.success('Doctor' + this.doctor.employeeId + 'Deleted Successfully', 'Doctor Delete Status');
        } else {
          this.doctorFlag = true;
          this.toast.error('Doctor' + this.doctor.employeeId + 'Deleted Failed', 'Doctor Delete Status');
        }
      }, error => this.toast.error('Doctor' + this.doctor.employeeId + 'Deleted Failed' + error, 'Doctor Delete Status'));
    }
  }

  addTimeDoctor() {
    if (this.doctorTimeTemp === null || this.doctorTimeTemp === undefined) this.toast.error('Please Enter a valid time', 'Doctor Time');
    else {
      const doctorAvailableTime = new DoctorAvailableTimes();
      doctorAvailableTime.doctorId = this.doctor.employeeId;
      doctorAvailableTime.timing = this.doctorTimeTemp;
      this.doctorService.addAvailableTime(doctorAvailableTime).subscribe(value => {
        if (value === null || value === undefined) {
          this.toast.error('Something went wrong', 'Add Time to Doctor ' + this.doctor.employeeId + ' Status');
        } else {
          this.toast.success('Time added successfully', 'Add Time to Doctor ' + this.doctor.employeeId + ' Status');
          this.doctorTimes.push(value);
        }
      }, error => this.toast.error('Something went wrong', 'Add Time to Doctor ' + error + this.doctor.employeeId + ' Status'));
    }
  }

  deleteDoctorTime(doctorTime: DoctorAvailableTimes) {
    this.doctorService.removeAvailableTime(doctorTime).subscribe(value => {
      if (value.status === 200) {
        const index = this.doctorTimes.indexOf(doctorTime);
        if (index > -1) {
          this.doctorTimes.splice(index, 1);
          this.toast.success('Time Removed Successfully', 'Remove Time Status');
        } else {
          this.toast.error('Something went wrong', 'Remove Time to Doctor ' + this.doctor.employeeId + ' Status')
        }
      } else {
        this.toast.error('Something went wrong', 'Remove Time to Doctor ' + this.doctor.employeeId + ' Status')
      }
    }, error => this.toast.error('Something went wrong', 'Remove Time to Doctor ' + error + this.doctor.employeeId + ' Status'));
  }
}
