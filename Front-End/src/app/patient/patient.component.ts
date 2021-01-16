import {Component, OnInit} from '@angular/core';
import {Patient} from '../model/Patient';
import {PatientService} from '../patient.service';
import {ToastrService} from 'ngx-toastr';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {

  patient: Patient = new Patient();
  patients: Array<Patient>;
  firstName = '';
  lastName = '';

  constructor(private patientService: PatientService,
              private toast: ToastrService, private datePipe: DatePipe) {
    this.patient.isAdmitted = false;
  }

  ngOnInit(): void {
    this.patientService.getAll().subscribe(value => this.patients = value);
  }

  onSubmitPatient = () => {
    if (this.patient === null || this.patient === undefined) this.toast.error('Please Enter valid values', 'Patient Creation');
    this.toast.clear();
    this.patient.patientDateOfBirth = this.datePipe.transform(this.patient.patientDateOfBirth, 'yyyy-MM-dd');
    this.patient.patientName = this.firstName + ' ' + this.lastName;
    this.patient.gender = this.patient.gender.toUpperCase();
    if (this.patient.patientId !== undefined) {
      this.patientService.updatePatient(this.patient).subscribe(updatedPatient => {
        this.patient = updatedPatient;
        this.toast.success('Patient Updated Successfully', 'Patient ' + this.patient.patientId + ' Status');
      });
    } else {
      this.patientService.createPatient(this.patient).subscribe(createdPatient => {
        this.patient = createdPatient;
        this.toast.success('Patient Added Successfully', 'Patient ' + this.patient.patientId + ' Status');
      });
    }
  }

  deletePatient(patient: Patient) {
    this.toast.clear();
    this.patientService.deletePatient(patient.patientId).subscribe(value => {
      if (value.status === 200) {
        const index = this.patients.indexOf(patient);
        if (index > -1) {
          this.patients.splice(index, 1);
          this.toast.success('Patient ' + patient.patientId + ' Deleted Successfully\n', 'Patient Delete Status');
        } else {
          this.toast.error('Patient ' + patient.patientId + ' Delete Failed!!\n', 'Patient Delete Status');
        }
      } else {
        this.toast.error('Patient ' + patient.patientId + ' Delete Failed!!\n', 'Patient Delete Status');
      }
    }, error => this.toast.error('Patient with ID ' + patient.patientId + ' Delete Failed!!\n' + error, 'Patient Delete Status'));
  }
}
