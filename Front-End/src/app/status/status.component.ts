import {Component, OnInit} from '@angular/core';
import {FeeService} from '../fee.service';
import {PatientEntryService} from '../patient-entry.service';
import {RoomService} from '../room.service';
import {PatientService} from '../patient.service';
import {InventoryService} from '../inventory.service';
import {EmployeeService} from '../employee.service';

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
  salaryFreq: any = [];
  chartIsReady: boolean;
  jobTypePieChartIsReady: boolean;
  salaryBarChartIsReady: boolean;
  patientEntryAnalysisIsReady: boolean;
  jobTypePieChartData: any = [];
  jobTypeTempNames: Array<string> = new Array<string>();
  jobTypeTempFreq: Array<number> = new Array<number>();
  jobTypePieChartLabels: Array<string> = new Array<string>();
  salaryNumbers: Array<string> = new Array<string>();
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
        'rgba(255, 206, 86, 1)',
        'rgb(166,1,255 , 1)',
        'rgba(243,1,1,1)',
        'rgb(1,243,207,1)',
        'rgb(255,119,0,1)',
        'rgb(183,21,255,1)'
      ], backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255,206,86,0.2)',
        'rgba(166,1,255 ,0.2)',
        'rgba(243,1,1,0.2)',
        'rgb(1,243,207,0.2)',
        'rgb(255,119,0,0.2)',
        'rgb(183,21,255,0.2)'
      ]
    }
  ];
  barChartOptions = {
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    },
    legend: {
      display: false
    },
    elements: {
      point: {
        radius: 0
      }
    }
  };
  barChartColors = [
    {
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(255, 159, 64, 0.2)',
        'rgba(243,1,1,0.2)',
        'rgb(1,243,207,0.2)',
        'rgb(255,119,0,0.2)',
        'rgb(183,21,255,0.2)'
      ],
      borderColor: [
        'rgba(255,99,132,1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)',
        'rgba(243,1,1,1)',
        'rgb(1,243,207,1)',
        'rgb(255,119,0,1)',
        'rgb(183,21,255,1)'
      ]
    }
  ];

  constructor(private feeService: FeeService, private patientEntryService: PatientEntryService,
              private employeeService: EmployeeService, private roomService: RoomService, private patentService: PatientService
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
      this.genderAnalysis = value;
      this.genderPieChartData = [
        {
          data: [this.genderAnalysis.x, this.genderAnalysis.total - this.genderAnalysis.x]
        }
      ];
      this.chartIsReady = true;
    });
    this.inventoryService.getNumberOfBloodUnits().subscribe(value => this.numberOfBloodUnits = value);
    this.patentService.getNumberOfAdmitted().subscribe(value => {
      this.patientEntryAnalysis = value;
      this.patientEntryAnalysisIsReady = true;
    });
    this.employeeService.getSalaryFreq().subscribe(value => {
      value.sort((a, b) => (a.x > b.x) ? -1 : 1);
      value.map(statNumber => statNumber.x).map(x => {
        this.salaryFreq.push({
          data: x,
          borderWidth: 1,
          fill: false
        });
      });
      value.map(statNumber => statNumber.total).map(total => this.salaryNumbers.push(total.toString()));
      this.salaryBarChartIsReady = true;
    });
    this.employeeService.getJobTypeFreq().subscribe(value => {
      value.forEach(statNumber => {
        this.jobTypeTempFreq.push(statNumber.freq);
        this.jobTypeTempNames.push(statNumber.name);
      })
    });
    this.jobTypePieChartLabels = this.jobTypeTempNames;
    this.jobTypePieChartData = [
      {
        data: this.jobTypeTempFreq
      }
    ];
    this.jobTypePieChartIsReady = true;
  }

}
