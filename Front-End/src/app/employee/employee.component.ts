import {Component, OnInit} from '@angular/core';
import {Employee} from '../model/Employee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  employee: Employee;

  constructor() { }

  ngOnInit(): void {
  }
}
