import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Room} from './model/Room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private roomURL = 'http://localhost:8080/hospital/room/';
  constructor(private http: HttpClient) { }

  getRoomById(roomNumber: number) {
    return this.http.get<Room>(this.roomURL + roomNumber);
}
}
