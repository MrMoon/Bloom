import {Component, OnInit} from '@angular/core';
import {Inventory} from '../model/Inventory';
import {InventoryService} from '../inventory.service';
import {ToastrService} from 'ngx-toastr';
import {NurseService} from '../nurse.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.scss']
})
export class InventoryComponent implements OnInit {

  inventory: Inventory = new Inventory();
  flag = false;

  constructor(private inventoryService: InventoryService
    , private nurseService: NurseService, private toast: ToastrService) {
    this.inventory.inventoryAmount = 0;
  }

  ngOnInit(): void {

  }

  clearAmount = () => {
    if (!this.flag) {
      this.inventory.inventoryAmount = undefined;
      this.flag = true;
    }
  }

  onInventorySubmit = () => {
    if ((this.inventory.inventoryId !== undefined && this.inventory.inventoryMangedBy !== undefined) ||
      (this.inventory.inventoryId === null) || (this.inventory.inventoryMangedBy === null)) {
      this.inventoryService.updateInventory(this.inventory).subscribe(updatedInventory => {
        console.log(updatedInventory);
        if (updatedInventory === null) {
          this.toast.error('Inventory Update Failed, Nurse ' + this.inventory.inventoryMangedBy + ' should be RANK 1\n', 'Inventory Update Status')
        } else {
          this.inventory = updatedInventory;
          this.toast.success('Inventory Updated Successfully', 'Inventory ' + this.inventory.inventoryId + ' Status');
        }
      }, error => this.toast.error('Inventory Update Failed\n' + error, 'Inventory Update Status'));
    } else {
      this.inventoryService.createInventory(this.inventory).subscribe(savedInventory => {
        if (savedInventory === null) this.toast.error('Inventory Creation Failed, Nurse ' + this.inventory.inventoryMangedBy + ' should be RANK 1\n', 'Inventory Creation Status')
        else {
          this.inventory = savedInventory;
          this.toast.success('Inventory Created Successfully', 'Inventory ' + this.inventory.inventoryId + ' Status');
        }
      }, error => this.toast.error('Inventory Creation Failed\n' + error, 'Inventory Creation Status'));
    }
  }
}
