import {Component, OnInit} from '@angular/core';
import {Inventory} from '../model/Inventory';
import {InventoryService} from '../inventory.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.scss']
})
export class InventoryComponent implements OnInit {

  inventories: Array<Inventory>;
  inventory: Inventory = new Inventory();
  flag = false;

  constructor(private inventoryService: InventoryService, private toast: ToastrService) {
    this.inventory.inventoryAmount = 0;
  }

  ngOnInit(): void {
    this.inventoryService.getAll().subscribe(value => this.inventories = value);
  }

  clearAmount = () => {
    if (!this.flag) {
      this.inventory.inventoryAmount = undefined;
      this.flag = true;
    }
  }

  onInventorySubmit = () => {
    if (this.inventory.inventoryId !== undefined && this.inventory.inventoryMangedBy !== undefined) {
      this.inventoryService.updateInventory(this.inventory).subscribe(updatedInventory => {
        this.inventory = updatedInventory;
        this.toast.success('Inventory Updated Successfully', 'Inventory ' + this.inventory.inventoryId + ' Status');
      }, error => this.toast.error('Inventory Update Failed\n' + error, 'Inventory Update Status'));
    } else {
      this.inventoryService.createInventory(this.inventory).subscribe(savedInventory => {
        this.inventory = savedInventory;
        this.toast.success('Inventory Created Successfully', 'Inventory ' + this.inventory.inventoryId + ' Status');
      }, error => this.toast.error('Inventory Creation Failed\n' + error, 'Inventory Creation Status'));
    }
  }
}
