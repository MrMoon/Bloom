import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PatientRoomDetails} from './model/PatientRoomDetails';
import {Doctor} from './model/Doctor';

@Injectable({
  providedIn: 'root'
})
export class PatientJoinService {

  private patientJoinURL = 'http://localhost:8080/patient/';

  constructor(private http: HttpClient) {
  }

  getPatientRoomDetails(patientId: number): Observable<PatientRoomDetails> {
    return this.http.get<PatientRoomDetails>(this.patientJoinURL + 'room/' + patientId);
  }

  getPatientDoctor(patientId: number): Observable<Doctor> {
    return this.http.get<Doctor>(this.patientJoinURL + 'doctor/' + patientId);
  }
}
