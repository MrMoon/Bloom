import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Patient} from './model/Patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private patientURL = 'http://localhost:8080/patient/';

  constructor(private http: HttpClient) {
  }

  getPatientById(patientId: number): Observable<Patient> {
    return this.http.get<Patient>(this.patientURL + patientId);
  }

  createPatient(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(this.patientURL, patient);
  }

  updatePatient(patient: Patient): Observable<Patient> {
    return this.http.put<Patient>(this.patientURL, patient);
  }

  deletePatient(patientId: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.patientURL + patientId, {observe: 'response'});
  }
}
