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

  getPatientEntryById = (patientEntryId: number): Observable<PatientEntry> =>
    this.http.get<PatientEntry>(this.patientEntryURL + patientEntryId);

  createPatientEntry = (patientEntry: PatientEntry): Observable<PatientEntry> =>
    this.http.post<PatientEntry>(this.patientEntryURL, patientEntry);

  updatePatientEntry = (patientEntry: PatientEntry): Observable<PatientEntry> =>
    this.http.put<PatientEntry>(this.patientEntryURL, patientEntry);

  deletePatientEntry = (patientEntryId: number): Observable<HttpResponse<any>> =>
    this.http.delete(this.patientEntryURL + patientEntryId, {observe: 'response'});

  getPatientByEntryId = (patientEntryId: number): Observable<Patient> =>
    this.http.get<Patient>(this.patientEntryURL + 'patient/' + patientEntryId);

  getAll = (): Observable<Array<PatientEntry>> => this.http.get<Array<PatientEntry>>(this.patientEntryURL);

  getNumberOfEntriesByType = (type: string): Observable<number> => this.http.get<number>(this.patientEntryURL + 'number/type/' + type);
}
