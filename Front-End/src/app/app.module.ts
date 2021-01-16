import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {AppRoutingModule} from './app-routing.module';
import {DatePipe} from '@angular/common'

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {CarouselModule} from 'ngx-owl-carousel-o';
import {AppComponent} from './app.component';
import {NavbarComponent} from './shared/navbar/navbar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ToastrModule} from 'ngx-toastr';
import {SpinnerComponent} from './shared/spinner/spinner.component';
import {ContentAnimateDirective} from './shared/directives/content-animate.directive';
import {NgSelectModule} from '@ng-select/ng-select';
import {EmployeeComponent} from './employee/employee.component';
import {PatientComponent} from './patient/patient.component';
import {FeeComponent} from './fee/fee.component';
import {PatientEntryComponent} from './patient-entry/patient-entry.component';
import {InventoryComponent} from './inventory/inventory.component';
import {RoomComponent} from './room/room.component';
import {PatientStatusComponent} from './patient-status/patient-status.component';
import {DoctorPatientComponent} from './doctor-patient/doctor-patient.component';
import {StatusComponent} from './status/status.component';
import {ChartsModule, ThemeService} from 'ng2-charts';
import {NurseComponent} from './nurse/nurse.component';
import {DoctorComponent} from './doctor/doctor.component';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/');
}

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SpinnerComponent,
    ContentAnimateDirective,
    EmployeeComponent,
    PatientComponent,
    FeeComponent,
    PatientEntryComponent,
    InventoryComponent,
    RoomComponent,
    PatientStatusComponent,
    DoctorPatientComponent,
    StatusComponent,
    NurseComponent,
    DoctorComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    CarouselModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    NgSelectModule,
    ChartsModule
  ],
  providers: [ThemeService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
