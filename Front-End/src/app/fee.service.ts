import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Fee} from './model/Fee';

@Injectable({
  providedIn: 'root'
})
export class FeeService {

  private feeURL = 'http://localhost:8080/hospital/fee/';

  constructor(private http: HttpClient) {
  }

  getFeeByNumber(feeNumber: number): Observable<Fee> {
    return this.http.get<Fee>(this.feeURL + feeNumber);
  }

  createFee(fee: Fee): Observable<Fee> {
    return this.http.post<Fee>(this.feeURL, fee);
  }

  updateFee(fee: Fee): Observable<Fee> {
    return this.http.put<Fee>(this.feeURL, fee);
  }

  deleteFee(feeNumber: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.feeURL + feeNumber, {observe: 'response'});
  }

  getPatientFeesByPatientId(patientId: number): Observable<Array<Fee>> {
    return this.http.get<Array<Fee>>(this.feeURL + 'patient/' + patientId);
  }

  getPatientFeesAmountByPatientId(patientId: number): Observable<number> {
    return this.http.get<number>(this.feeURL + 'patient/price/' + patientId);
  }
}
