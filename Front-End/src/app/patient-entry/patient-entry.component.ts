import {Component, OnInit} from '@angular/core';
import {PatientEntry} from '../model/PatientEntry';
import {PatientEntryService} from '../patient-entry.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-patient-entry',
  templateUrl: './patient-entry.component.html',
  styleUrls: ['./patient-entry.component.scss']
})
export class PatientEntryComponent implements OnInit {

  patientEntry: PatientEntry = new PatientEntry();

  constructor(private patientEntryService: PatientEntryService, private toast: ToastrService) {
  }

  ngOnInit(): void {
  }

  onPatientEntrySubmit = () => {
    if (this.patientEntry.patientEntryId !== undefined) {
      this.patientEntryService.updatePatientEntry(this.patientEntry).subscribe(updatedPatientEntry => {
        this.patientEntry = updatedPatientEntry;
        this.toast.success('Patient Entry Updated Successfully', 'Patient Entry  ' + this.patientEntry.patientEntryId + ' Status');
      }, error => this.toast.error('Patient Entry Update Failed\n' + error, 'Patient Entry Update Status'));
    } else {
      this.patientEntryService.createPatientEntry(this.patientEntry).subscribe(savedPatientEntry => {
        this.patientEntry = savedPatientEntry;
        this.toast.success('Patient Entry Created Successfully', 'Patient Entry  ' + this.patientEntry.patientEntryId + ' Status');
      }, error => this.toast.error('Patient Entry Creation Failed\n' + error, 'Patient Entry Creation Status'));
    }
  }
}
