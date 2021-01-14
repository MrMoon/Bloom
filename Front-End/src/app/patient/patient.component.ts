import {Component, OnInit} from '@angular/core';
import {Patient} from '../model/Patient';
import {PatientService} from '../patient.service';
import {ToastrService} from 'ngx-toastr';

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

  constructor(private patientService: PatientService, private toast: ToastrService) {
    this.patient.isAdmitted = false;
  }

  ngOnInit(): void {
    this.patientService.getAll().subscribe(value => this.patients = value);
  }

  onSubmitPatient = () => {
    this.toast.clear();
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
}
