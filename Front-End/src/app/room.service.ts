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

  getRoomById(roomNumber: number): Observable<Room> {
    return this.http.get<Room>(this.roomURL + roomNumber);
  }

  createRoom(room: Room): Observable<Room> {
    return this.http.post<Room>(this.roomURL, room);
  }

  updateRoom(room: Room): Observable<Room> {
    return this.http.put<Room>(this.roomURL, room);
  }

  deleteRoom(roomId: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.roomURL + roomId, {observe: 'response'});
  }
}
