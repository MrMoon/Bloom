import {Component, OnInit} from '@angular/core';
import {PatientEntry} from '../model/PatientEntry';
import {PatientEntryService} from '../patient-entry.service';
import {ToastrService} from 'ngx-toastr';
import {PatientService} from '../patient.service';

@Component({
  selector: 'app-patient-entry',
  templateUrl: './patient-entry.component.html',
  styleUrls: ['./patient-entry.component.scss']
})
export class PatientEntryComponent implements OnInit {

  patientEntry: PatientEntry = new PatientEntry();
  patientEntries: Array<PatientEntry>;

  constructor(private patientEntryService: PatientEntryService, private patientService: PatientService
    , private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.patientEntryService.getAll().subscribe(value => this.patientEntries = value);
  }

  onPatientEntrySubmit = () => {
    if (this.patientEntry === null || this.patientEntry === undefined) this.toast.error('Please Enter valid values', 'Patient Entry Creation');
    console.log(this.patientEntry);
    this.patientEntry.patientEntryType = this.patientEntry.patientEntryType.toUpperCase();
    if (this.patientEntry.patientEntryId !== undefined) {
      this.patientEntryService.updatePatientEntry(this.patientEntry).subscribe(updatedPatientEntry => {
        this.patientEntry = updatedPatientEntry;
        this.toast.success('Patient Entry Updated Successfully', 'Patient Entry  ' + this.patientEntry.patientEntryId + ' Status');
      }, error => this.toast.error('Patient Entry Update Failed\n' + error, 'Patient Entry Update Status'));
    } else {
      this.patientEntryService.createPatientEntry(this.patientEntry).subscribe(savedPatientEntry => {
        this.patientEntry = savedPatientEntry;
        this.patientService.getPatientById(this.patientEntry.patientId).subscribe(patient => {
          patient.isAdmitted = true;
          this.patientService.updatePatient(patient).subscribe(updatedPatient => updatedPatient.isAdmitted);
        });
        this.toast.success('Patient Entry Created Successfully', 'Patient Entry  ' + this.patientEntry.patientEntryId + ' Status');
      }, error => this.toast.error('Patient Entry Creation Failed\n' + error, 'Patient Entry Creation Status'));
    }
  }

  deleteEntry(entry: PatientEntry) {
    this.toast.clear();
    this.patientEntryService.deletePatientEntry(entry.patientEntryId).subscribe(value => {
      if (value.status === 200) {
        const index = this.patientEntries.indexOf(entry);
        if (index > -1) {
          this.patientEntries.splice(index, 1);
          this.toast.success('Patient Entry Deleted Successfully', 'Patient Entry Deleted Status');
        } else {
          this.toast.error('Patient Entry Delete Failed\n', 'Patient Entry Delete Status')
        }
      } else {
        this.toast.error('Patient Entry Delete Failed\n', 'Patient Entry Delete Status')
      }
    }, error => this.toast.error('Patient Entry Delete Failed\n' + error, 'Patient Entry Delete Status'));
  }
}
