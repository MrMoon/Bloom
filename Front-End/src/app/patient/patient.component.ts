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
  firstName = '';
  lastName = '';

  constructor(private patientService: PatientService, private toast: ToastrService) {
    this.patient.isAdmitted = false;
  }

  ngOnInit(): void {

  }

  onSubmitPatient(): void {
    this.toast.clear();
    this.patient.patientName = this.firstName + ' ' + this.lastName;
    this.patient.gender = this.patient.gender.toUpperCase();
    if (this.patient.patientId !== undefined) {
      console.log(this.patient);
      this.patientService.updatePatient(this.patient).subscribe(updatedPatient => {
        this.patient = updatedPatient;
        this.toast.success('Patient Updated Successfully', 'Patient ' + this.patient.patientId + ' Status');
      }, error => console.error(error));
    } else {
      console.log(this.patient);
      this.patientService.createPatient(this.patient).subscribe(createdPatient => {
        this.patient = createdPatient;
        this.toast.success('Patient Added Successfully', 'Patient ' + this.patient.patientId + ' Status');
      }, error => console.error(error));
    }
  }
}
