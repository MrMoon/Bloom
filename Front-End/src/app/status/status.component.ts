import {Component, OnInit} from '@angular/core';
import {FeeService} from '../fee.service';
import {PatientEntryService} from '../patient-entry.service';
import {RoomService} from '../room.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss']
})
export class StatusComponent implements OnInit {

  feeAmount: number;
  feeAmountDiff: number;
  feeAmountSign: boolean;
  numberOfEmergencies: number;
  numberOfLuxuryRooms: number;
  numberOfUsedRooms: number;
  numberOfUsedRoomsDiff: number;

  constructor(private feeService: FeeService, private patientEntryService: PatientEntryService, private roomService: RoomService) {
  }

  getPercentageDiff = (x: number, y: number): number => ((x - y) / (x + y)) * 100;

  ngOnInit(): void {
    this.feeService.getFeeTotalInDay().subscribe(value => this.feeAmount = value);
    this.feeService.getFeeAnalysis().subscribe(value => {
      this.feeAmountDiff = this.getPercentageDiff(value.x, value.total);
      this.feeAmountSign = this.feeAmountDiff > 0;
      this.feeAmountDiff = Number(this.feeAmountDiff.toFixed(2));
    });
    this.patientEntryService.getNumberOfEntriesByType('EMERGENCY').subscribe(value => this.numberOfEmergencies = value);
    this.roomService.getLuxuryRoomsNumber().subscribe(value => this.numberOfLuxuryRooms = value);
    this.roomService.getAnalysis().subscribe(value => {
      this.numberOfUsedRooms = value.x;
      this.numberOfUsedRoomsDiff = Number(this.getPercentageDiff(value.x, value.total).toFixed(2));
    });
  }

}
