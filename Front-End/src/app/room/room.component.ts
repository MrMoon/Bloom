import {Component, OnInit} from '@angular/core';
import {Room} from '../model/Room';
import {RoomService} from '../room.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.scss']
})
export class RoomComponent implements OnInit {

  room: Room = new Room();
  rooms: Array<Room> = new Array<Room>();

  constructor(private roomService: RoomService, private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.roomService.getAll().subscribe(value => this.rooms = value);
  }

  onSubmitRoom(): void {
    this.toast.clear();
    if (this.room.roomId !== undefined) {
      this.roomService.updateRoom(this.room).subscribe(updatedRoom => {
        this.room = updatedRoom;
        this.toast.success('Room Updated Successfully', 'Room ' + this.room.roomId + ' Status');
      });
    } else {
      this.roomService.createRoom(this.room).subscribe(createdRoom => {
        this.room = createdRoom;
        this.toast.success('Room Created Successfully', 'Room ' + this.room.roomId + ' Status');
        this.rooms.push(this.room);
      });
    }
  }

  deleteRoom(roomId: number): void {
    console.log(roomId);
    this.roomService.deleteRoom(roomId).subscribe(value => {
      this.toast.clear();
      if (value.status === 200) this.toast.success('Room Deleted Successfully', 'Room ' + roomId + ' Status');
    }, error => this.toast.error('Something went wrong, error ' + error.toString(), 'Room ' + roomId + ' Status'));
  }

}
