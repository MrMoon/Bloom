import {Component, OnInit} from '@angular/core';
import {Room} from '../model/Room';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent implements OnInit {

  room: Room;
  rooms: Array<Room>;

  constructor() {
  }

  ngOnInit(): void {
  }

}
