import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Room} from './model/Room';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private roomURL = 'http://localhost:8080/hospital/room/';

  constructor(private http: HttpClient) {
  }

  getRoomById = (roomNumber: number): Observable<Room> => this.http.get<Room>(this.roomURL + roomNumber);

  createRoom = (room: Room): Observable<Room> => this.http.post<Room>(this.roomURL, room);

  updateRoom = (room: Room): Observable<Room> => this.http.put<Room>(this.roomURL, room);

  deleteRoom = (roomId: number): Observable<HttpResponse<any>> =>
    this.http.delete(this.roomURL + roomId, {observe: 'response'});

  getAll = (): Observable<Array<Room>> => this.http.get<Array<Room>>(this.roomURL);
}
