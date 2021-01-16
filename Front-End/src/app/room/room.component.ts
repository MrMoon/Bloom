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

  deleteRoom(room: Room): void {
    this.toast.clear();
    this.roomService.deleteRoom(room.roomId).subscribe(value => {
      if (value.status === 200) {
        const index = this.rooms.indexOf(room);
        if (index > -1) {
          this.rooms.splice(index, 1);
          this.toast.success('Room Deleted Successfully', 'Room ' + room.roomId + ' Status');
        } else {
          this.toast.error('Something went wrong, error ', 'Room ' + room.roomId + ' Status')
        }
      }
    }, error => this.toast.error('Something went wrong, error ' + error.toString(), 'Room ' + room.roomId + ' Status'));
  }

}
