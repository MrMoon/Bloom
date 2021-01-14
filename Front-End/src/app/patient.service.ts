import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Patient} from './model/Patient';
import {PatientRoomDetails} from './model/PatientRoomDetails';
import {Doctor} from './model/Doctor';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private patientURL = 'http://localhost:8080/patient/';

  constructor(private http: HttpClient) {
  }

  getAll = (): Observable<Array<Patient>> => this.http.get<Array<Patient>>(this.patientURL);

  getPatientById = (patientId: number): Observable<Patient> => this.http.get<Patient>(this.patientURL + patientId);

  createPatient = (patient: Patient): Observable<Patient> => this.http.post<Patient>(this.patientURL, patient);

  updatePatient = (patient: Patient): Observable<Patient> => this.http.put<Patient>(this.patientURL, patient);

  deletePatient = (patientId: number): Observable<HttpResponse<any>> =>
    this.http.delete(this.patientURL + patientId, {observe: 'response'});

  getPatientRoomDetails = (patientId: number): Observable<PatientRoomDetails> =>
    this.http.get<PatientRoomDetails>(this.patientURL + 'room/' + patientId);

  getPatientDoctor = (patientId: number): Observable<Doctor> => this.http.get<Doctor>(this.patientURL + 'doctor/' + patientId);

}
