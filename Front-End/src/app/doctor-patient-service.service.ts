import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Patient} from './model/Patient';

@Injectable({
  providedIn: 'root'
})
export class DoctorPatientServiceService {

  private doctorPatientURL = 'http://localhost:8080/hospital/doctor/'

  constructor(private http: HttpClient) {
  }

  getDoctorPatientById(doctroId: number) {
    return this.http.get<Patient>(this.doctorPatientURL + doctroId);
  }
}
