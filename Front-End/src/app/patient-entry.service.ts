import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PatientEntry} from './model/PatientEntry';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Patient} from './model/Patient';

@Injectable({
  providedIn: 'root'
})
export class PatientEntryService {

  private patientEntryURL = 'http://localhost:8080/hospital/entry/';

  constructor(private http: HttpClient) {
  }

  getPatientEntryById(patientEntryId: number): Observable<PatientEntry> {
    return this.http.get<PatientEntry>(this.patientEntryURL + patientEntryId);
  }

  createPatientEntry(patientEntry: PatientEntry): Observable<PatientEntry> {
    return this.http.post<PatientEntry>(this.patientEntryURL, patientEntry);
  }

  updatePatientEntry(patientEntry: PatientEntry): Observable<PatientEntry> {
    return this.http.put<PatientEntry>(this.patientEntryURL, patientEntry);
  }

  deletePatientEntry(patientEntryId: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.patientEntryURL + patientEntryId, {observe: 'response'});
  }

  getPatientByEntryId(patientEntryId: number): Observable<Patient> {
    return this.http.get<Patient>(this.patientEntryURL + 'patient/' + patientEntryId);
  }
}
