import {Component, OnInit} from '@angular/core';
import {FeeService} from '../fee.service';
import {PatientEntryService} from '../patient-entry.service';
import {RoomService} from '../room.service';
import {PatientService} from '../patient.service';
import {InventoryService} from '../inventory.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss']
})
export class StatusComponent implements OnInit {

  feeAmount: number;
  feeAmountDiff: number;
  numberOfRooms: number;
  numberOfTeens: number;
  feeAmountSign: boolean;
  numberOfPatients: number;
  numberOfUsedRooms: number;
  numberOfUsedRoomSign: boolean;
  numberOfEmergencies: number;
  numberOfLuxuryRooms: number;
  numberOfUsedRoomsDiff: number;
  numberOfPatientTeenDiff: number;
  genderAnalysis: StatNumbers;
  numberOfBloodUnits: number;
  patientEntryAnalysis: StatNumbers;
  genderPieChartData: any;
  genderPieChartLabels = ['Female', 'Male'];
  doughnutPieChartOptions = {
    responsive: true,
    animation: {
      animateScale: true,
      animateRotate: true
    }
  };
  doughnutPieChartColors = [
    {
      borderColor: [
        'rgba(255,99,132,1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)'
      ], backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)'
      ]
    }
  ];
  charIsReady: boolean;

  constructor(private feeService: FeeService, private patientEntryService: PatientEntryService,
              private roomService: RoomService, private patentService: PatientService
    , private inventoryService: InventoryService) {
  }

  ngOnInit(): void {
    this.feeService.getFeeTotalInDay().subscribe(value => this.feeAmount = value);
    this.feeService.getFeeAnalysis().subscribe(value => {
      const x = value.x;
      const total = value.total;
      this.feeAmountDiff = ((x - total) / (total + x)) * 100;
      this.feeAmountSign = this.feeAmountDiff > 0;
      this.feeAmountDiff = Number(this.feeAmountDiff.toFixed(2));
    });
    this.patientEntryService.getNumberOfEntriesByType('EMERGENCY').subscribe(value => this.numberOfEmergencies = value);
    this.roomService.getLuxuryRoomsNumber().subscribe(value => this.numberOfLuxuryRooms = value);
    this.roomService.getAnalysis().subscribe(value => {
      this.numberOfUsedRooms = value.x;
      this.numberOfRooms = value.total;
      this.numberOfUsedRoomsDiff = Number(((this.numberOfUsedRooms / this.numberOfRooms) * 100).toFixed(0));
      this.numberOfUsedRoomSign = this.numberOfUsedRooms > 1;
    });
    this.patentService.getNumberTeensAnalysis().subscribe(value => {
      this.numberOfPatients = value.total;
      this.numberOfTeens = value.x;
      this.numberOfPatientTeenDiff = Number(((this.numberOfTeens / this.numberOfPatients) * 100).toFixed(2));
    });
    this.patentService.getGenderAnalysis().subscribe(value => {
      this.genderAnalysis = value; // Total = total number of patients , x = number of females
      this.genderPieChartData = [
        {
          data: [this.genderAnalysis.x, this.genderAnalysis.total - this.genderAnalysis.x]
        }
      ];
      this.charIsReady = true;
    });
    this.inventoryService.getNumberOfBloodUnits().subscribe(value => this.numberOfBloodUnits = value);
    this.patentService.getNumberOfAdmitted().subscribe(value => this.patientEntryAnalysis = value);
  }

}
