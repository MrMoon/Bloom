import {Component, OnInit} from '@angular/core';
import {PatientEntry} from '../model/PatientEntry';

@Component({
  selector: 'app-patient-entry',
  templateUrl: './patient-entry.component.html',
  styleUrls: ['./patient-entry.component.scss']
})
export class PatientEntryComponent implements OnInit {

  patientEntry: PatientEntry;

  constructor() {
  }

  ngOnInit(): void {
  }

}
