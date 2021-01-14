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

  patients: Array<Patient>;
  doctorId = '';

  constructor(private doctorService: DoctorService, private toast: ToastrService) {
  }

  ngOnInit(): void {
  }

  getDoctorPatients = () => {
    this.toast.clear();
    if (this.doctorId === undefined || this.doctorId.length === 0) this.toast.error('Please Enter a valid ID', 'Doctor ID');
    else {
      this.doctorService
        .getDoctorPatients(this.doctorId)
        .subscribe(doctorPatients => {
            this.patients = doctorPatients;
            if (this.patients.length === 0)
              this.toast.info('Doctor with ID (' + this.doctorId + ') have no patients', 'Doctor Patients Status');
          },
          error => this.toast.error('Something went wrong' + error.status, 'Getting Doctor Patient Failed!!!'));
    }
  }
}
