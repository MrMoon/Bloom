import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Doctor} from './model/Doctor';
import {Patient} from './model/Patient';
import {DoctorAvailableTimes} from './model/DoctorAvailableTimes';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private doctorURL = 'http://localhost:8080/employee/doctor/'

  constructor(private http: HttpClient) {
  }

  getDoctorById(doctorId: number): Observable<Doctor> {
    return this.http.get<Doctor>(this.doctorURL + doctorId);
  }

  createDoctor(doctor: Doctor): Observable<Doctor> {
    return this.http.post<Doctor>(this.doctorURL, doctor);
  }

  updateDoctor(doctor: Doctor): Observable<Doctor> {
    return this.http.put<Doctor>(this.doctorURL, doctor);
  }

  deleteDoctor(doctorId: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.doctorURL + doctorId, {observe: 'response'});
  }

  getDoctorPatients(doctorId: number): Observable<Array<Patient>> {
    return this.http.get<Array<Patient>>(this.doctorURL + 'patient/' + doctorId);
  }

  getDoctorFees(doctorId: number): Observable<number> {
    return this.http.get<number>(this.doctorURL + 'fee/' + doctorId);
  }

  getAvailableTimes(doctorId: number): Observable<Array<DoctorAvailableTimes>> {
    return this.http.get<Array<DoctorAvailableTimes>>(this.doctorURL + 'time/' + doctorId);
  }

  getAvailableDays(doctorId: number): Observable<Array<string>> {
    return this.http.get<Array<string>>(this.doctorURL + 'days/' + doctorId);
  }

  addAvailableTime(doctorAvailableTime: DoctorAvailableTimes): Observable<DoctorAvailableTimes> {
    return this.http.put<DoctorAvailableTimes>(this.doctorURL + 'add_time/', doctorAvailableTime);
  }

  removeAvailableTime(doctorAvailableTime: DoctorAvailableTimes): Observable<HttpResponse<any>> {
    return this.http.delete(this.doctorURL + 'remove_time/' + doctorAvailableTime, {observe: 'response'});
  }
}
