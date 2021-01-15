import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Inventory} from './model/Inventory';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private inventoryURL = 'http://localhost:8080/hospital/inventory/';

  constructor(private http: HttpClient) {
  }

  getInventoryById = (nurseId: number, inventoryId: number): Observable<Inventory> =>
    this.http.get<Inventory>(this.inventoryURL + inventoryId + '/' + nurseId);

  createInventory = (inventory: Inventory): Observable<Inventory> => this.http.post<Inventory>(this.inventoryURL, inventory);

  updateInventory = (inventory: Inventory): Observable<Inventory> => this.http.put<Inventory>(this.inventoryURL, inventory);

  deleteInventory = (nurseId: number, inventoryId: number): Observable<HttpResponse<any>> =>
    this.http.delete(this.inventoryURL + inventoryId + '/' + nurseId, {observe: 'response'});

  getAll = (): Observable<Array<Inventory>> => this.http.get<Array<Inventory>>(this.inventoryURL);

  getNumberOfBloodUnits = (): Observable<number> => this.http.get<number>(this.inventoryURL + 'number/BLOOD');
}
