import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoomComponent} from './room/room.component';
import {DoctorPatientComponent} from './doctor-patient/doctor-patient.component';
import {EmployeeComponent} from './employee/employee.component';
import {FeeComponent} from './fee/fee.component';
import {InventoryComponent} from './inventory/inventory.component';
import {PatientComponent} from './patient/patient.component';
import {PatientEntryComponent} from './patient-entry/patient-entry.component';
import {PatientStatusComponent} from './patient-status/patient-status.component';
import {StatusComponent} from './status/status.component';
import {NurseComponent} from './nurse/nurse.component';


const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'room', component: RoomComponent},
  {path: 'doctor-patient', component: DoctorPatientComponent},
  {path: 'employee', component: EmployeeComponent},
  {path: 'fee', component: FeeComponent},
  {path: 'inventory', component: InventoryComponent},
  {path: 'patient', component: PatientComponent},
  {path: 'patient-entry', component: PatientEntryComponent},
  {path: 'patient-status', component: PatientStatusComponent},
  {path: 'room', component: RoomComponent},
  {path: 'dashboard', component: StatusComponent},
  {path: 'nurse', component: NurseComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
