import {Component, OnInit} from '@angular/core';
import {Patient} from '../model/Patient';

@Component({
  selector: 'app-doctor-patient',
  templateUrl: './doctor-patient.component.html',
  styleUrls: ['./doctor-patient.component.scss']
})
export class DoctorPatientComponent implements OnInit {

  patients: Array<Patient>;

  constructor() {
    this.patients = new Array<Patient>();
  }

  ngOnInit(): void {

  }

}
