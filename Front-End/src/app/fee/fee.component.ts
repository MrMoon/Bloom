import {Component, OnInit} from '@angular/core';
import {Fee} from '../model/Fee';
import {FeeService} from '../fee.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-fee',
  templateUrl: './fee.component.html',
  styleUrls: ['./fee.component.scss']
})
export class FeeComponent implements OnInit {

  fee: Fee = new Fee();
  fees: Array<FeePatient>;
  flag = false;

  constructor(private feeService: FeeService, private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.fee.feeAmount = 0.00;
    this.feeService.getAll().subscribe(value => this.fees = value);
  }

  onSubmitFee = () => {
    if (this.fee.feePaymentNumber !== undefined) {
      this.feeService.updateFee(this.fee).subscribe(updatedFee => {
        this.fee = updatedFee;
        this.toast.success('Fee Updated Successfully', 'Fee ' + this.fee.feePaymentNumber + ' Status');
      }, error => this.toast.error('Fee Update Failed\n' + error, 'Fee Update Status'));
    } else {
      this.feeService.createFee(this.fee).subscribe(savedFee => {
        this.fee = savedFee;
        this.toast.success('Fee Created Successfully', 'Fee ' + this.fee.feePaymentNumber + ' Status');
      }, error => this.toast.error('Fee Creation Failed\n' + error, 'Fee Creation Status'));
    }
  }

  clearAmount = () => {
    if (!this.flag) {
      this.fee.feeAmount = undefined;
      this.flag = true;
    }
  }
}
