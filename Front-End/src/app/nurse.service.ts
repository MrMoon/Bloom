import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Nurse} from './model/Nurse';
import {Inventory} from './model/Inventory';

@Injectable({
  providedIn: 'root'
})
export class NurseService {

  private nurseURL = 'http://localhost:8080/employee/nurse/';

  constructor(private http: HttpClient) {
  }

  getNurseById = (nurseId: string): Observable<Nurse> => this.http.get<Nurse>(this.nurseURL + nurseId);

  createNurse = (nurse: Nurse): Observable<Nurse> => this.http.post<Nurse>(this.nurseURL, nurse);

  updateNurse = (nurse: Nurse): Observable<Nurse> => this.http.put<Nurse>(this.nurseURL, nurse);

  deleteNurse = (nurseId: string): Observable<HttpResponse<any>> => this.http.delete(this.nurseURL + nurseId, {observe: 'response'});

  getNurseInventories = (nurseId: string): Observable<Array<Inventory>> =>
    this.http.get<Array<Inventory>>(this.nurseURL + 'inventories/' + nurseId);


}
