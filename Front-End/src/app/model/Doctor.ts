import {Employee} from './Employee';
import {DoctorAvailableTimes} from './DoctorAvailableTimes';

export class Doctor extends Employee{
  doctorAvailableDays: string;
  doctorAvailableTimes: Array<DoctorAvailableTimes>;
}
