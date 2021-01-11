import {Component, OnInit} from '@angular/core';
import {Fee} from '../model/Fee';

@Component({
  selector: 'app-fee',
  templateUrl: './fee.component.html',
  styleUrls: ['./fee.component.scss']
})
export class FeeComponent implements OnInit {

  fee: Fee;

  constructor() {
  }

  ngOnInit(): void {
  }

}
