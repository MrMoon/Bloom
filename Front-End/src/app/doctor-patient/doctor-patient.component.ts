import {Component, OnInit} from '@angular/core';
import {Patient} from '../model/Patient';
import {DoctorService} from '../doctor.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-doctor-patient',
  templateUrl: './doctor-patient.component.html',
  styleUrls: ['./doctor-patient.component.scss']
})
export class DoctorPatientComponent implements OnInit {

  patients: Array<Patient> = new Array<Patient>();
  doctorId = '';

  constructor(private doctorService: DoctorService, private toast: ToastrService) {
  }

  ngOnInit(): void {
  }

  getDoctorPatients = () => {
    if (this.doctorId === undefined || this.doctorId.length === 0) {
      this.toast.error('Please Enter a valid ID', 'Doctor ID');
      return;
    }
    this.doctorService
      .getDoctorPatients(this.doctorId)
      .subscribe(doctorPatients => this.patients = doctorPatients,
        error => this.toast.error('Something went wrong ' + error, 'Getting Doctor Patient Failed!!!'));
  }
}
