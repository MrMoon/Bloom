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

  getDoctorById = (doctorId: string): Observable<Doctor> => this.http.get<Doctor>(this.doctorURL + doctorId);

  createDoctor = (doctor: Doctor): Observable<Doctor> => this.http.post<Doctor>(this.doctorURL, doctor);

  updateDoctor = (doctor: Doctor): Observable<Doctor> => this.http.put<Doctor>(this.doctorURL, doctor);

  deleteDoctor = (doctorId: number): Observable<HttpResponse<any>> => this.http.delete(this.doctorURL + doctorId, {observe: 'response'});

  getDoctorPatients = (doctorId: string): Observable<Array<Patient>> =>
    this.http.get<Array<Patient>>(this.doctorURL + 'patient/' + doctorId);

  getDoctorFees = (doctorId: string): Observable<number> => this.http.get<number>(this.doctorURL + 'fee/' + doctorId);

  getAvailableTimes = (doctorId: number): Observable<Array<DoctorAvailableTimes>> =>
    this.http.get<Array<DoctorAvailableTimes>>(this.doctorURL + 'time/' + doctorId);

  getAvailableDays = (doctorId: string): Observable<Array<string>> => this.http.get<Array<string>>(this.doctorURL + 'days/' + doctorId);

  addAvailableTime = (doctorAvailableTime: DoctorAvailableTimes): Observable<DoctorAvailableTimes> =>
    this.http.put<DoctorAvailableTimes>(this.doctorURL + 'add_time/', doctorAvailableTime);

  removeAvailableTime = (doctorAvailableTime: DoctorAvailableTimes): Observable<HttpResponse<any>> =>
    this.http.post<HttpResponse<any>>(this.doctorURL + 'remove_time/', doctorAvailableTime, {observe: 'response'});
}
