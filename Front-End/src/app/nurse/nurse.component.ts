import {Component, OnInit} from '@angular/core';
import {NurseService} from '../nurse.service';
import {Nurse} from '../model/Nurse';
import {ToastrService} from 'ngx-toastr';
import {Inventory} from '../model/Inventory';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-nurse',
  templateUrl: './nurse.component.html',
  styleUrls: ['./nurse.component.scss']
})
export class NurseComponent implements OnInit {

  nurseId = '';
  nurse: Nurse;
  nurseFlag: boolean;
  inventories: Array<Inventory> = new Array<Inventory>();
  nurseRanks = ['ONE', 'TWO', 'THREE', 'FOUR', 'FIVE'];
  nurseShifts = ['A', 'B', 'C'];

  constructor(private nurseService: NurseService, private toast: ToastrService, private datePipe: DatePipe) {
  }

  ngOnInit(): void {
  }

  getNurseDetails() {
    this.toast.clear();
    if (this.nurseId === undefined || this.nurseId.length === 0) this.toast.error('Please Enter a valid ID', 'Nurse ID');
    this.nurseService.getNurseById(this.nurseId).subscribe(value => {
      if (value === null || value === undefined) {
        this.toast.error('Nurse with ID ' + this.nurseId + ' Does not exist', 'Nurse ' + this.nurseId + ' Status');
        this.nurseFlag = false;
      } else {
        this.nurse = value;
        this.nurseFlag = true;
      }
    });
    this.nurseService.getNurseInventories(this.nurseId).subscribe(value => this.inventories = value);
  }

  updatePatient() {
    this.nurse.employeeDateOfBirth = this.datePipe.transform(this.nurse.employeeDateOfBirth, 'yyyy-MM-dd');
    this.toast.clear();
    this.nurseService.updateNurse(this.nurse).subscribe(value => {
      this.nurse = value;
      this.toast.success('Nurse ' + this.nurse.employeeId + ' Updated Successfully', 'Nurse Update Status');
    }, error => this.toast.error('Nurse ' + this.nurse.employeeId + ' Update Failed!!\n' + error, 'Nurse Update Status'));
  }

  deleteNurse() {
    this.nurseService.deleteNurse(this.nurse.employeeId.toString()).subscribe(value => {
      if (value.status === 200) {
        this.toast.success('Nurse ' + this.nurse.employeeId + ' Deleted Successfully', 'Nurse Delete Status');
        this.nurseFlag = false;
      } else {
        this.toast.error('Nurse ' + this.nurse.employeeId + ' Delete Failed!!\n', 'Nurse Delete Status');
        this.nurseFlag = true;
      }
    }, error => this.toast.error('Nurse ' + this.nurse.employeeId + ' Delete Failed!!\n' + error, 'Nurse Delete Status'));
  }
}
