import {Component, OnInit} from '@angular/core';
import {NurseService} from '../nurse.service';
import {Nurse} from '../model/Nurse';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-nurse',
  templateUrl: './nurse.component.html',
  styleUrls: ['./nurse.component.scss']
})
export class NurseComponent implements OnInit {

  nurseId = '';
  nurse: Nurse;

  constructor(private nurseService: NurseService, private toast: ToastrService) {
  }

  ngOnInit(): void {
  }

  getNurseDetails() {
    this.toast.clear();
    if (this.nurseId === undefined || this.nurseId.length === 0) this.toast.error('Please Enter a valid ID', 'Nurse ID');
    this.nurseService.getNurseById(this.nurseId).subscribe(value => this.nurse = value);
  }
}
