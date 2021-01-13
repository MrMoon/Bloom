import {Component, OnInit} from '@angular/core';
import {Patient} from '../model/Patient';
import {PatientService} from '../patient.service';

@Component({
  selector: 'app-patient-status',
  templateUrl: './patient-status.component.html',
  styleUrls: ['./patient-status.component.scss']
})
export class PatientStatusComponent implements OnInit {

  patients: Array<Patient> = new Array<Patient>();

  constructor(private patientService: PatientService) {

  }

  ngOnInit() {
    this.patientService.getAll().subscribe(allPatients => this.patients = allPatients);
  }

}
