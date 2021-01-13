import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Employee} from './model/Employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private employeeURL = 'http://localhost:8080/employee/';

  constructor(private http: HttpClient) {
  }

  getEmployeeById = (employeeId: number): Observable<Employee> => this.http.get<Employee>(this.employeeURL + employeeId);

  createEmployee = (employee: Employee): Observable<Employee> => this.http.post<Employee>(this.employeeURL, employee);

  updateEmployee = (employee: Employee): Observable<Employee> => this.http.put<Employee>(this.employeeURL, employee);

  deleteEmployee = (employeeId: number): Observable<HttpResponse<any>> => this.http.delete(this.employeeURL + employeeId, {observe: 'response'});
}
