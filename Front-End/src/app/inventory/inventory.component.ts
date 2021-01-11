import {Component, OnInit} from '@angular/core';
import {Inventory} from '../model/Inventory';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.scss']
})
export class InventoryComponent implements OnInit {

  inventory: Inventory;

  constructor() {
  }

  ngOnInit(): void {
  }

}
