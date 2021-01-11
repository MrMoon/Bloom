import {Component, OnInit} from '@angular/core';
import {Patient} from '../model/Patient';

@Component({
  selector: 'app-patient-status',
  templateUrl: './patient-status.component.html',
  styleUrls: ['./patient-status.component.scss']
})
export class PatientStatusComponent implements OnInit {

  patients: Array<Patient>;

  constructor() {

  }

  ngOnInit() {

  }

}
