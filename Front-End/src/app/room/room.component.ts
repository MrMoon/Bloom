import { Component, OnInit } from '@angular/core';
import {RoomService} from '../room.service';
import {Room} from '../model/Room';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent implements OnInit {

  room: Room;
  roomId = 1;

  constructor(private roomService: RoomService) { }

  ngOnInit(): void {
    this.roomService.getRoomById(this.roomId).subscribe(value => console.log(value.roomType));
  }

}
