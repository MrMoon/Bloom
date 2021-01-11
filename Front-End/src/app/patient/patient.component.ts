import {Component, OnInit} from '@angular/core';
import {Patient} from '../model/Patient';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {

  patient: Patient;

  constructor() {
  }

  ngOnInit(): void {
  }

}
