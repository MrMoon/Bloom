import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { AppRoutingModule } from './app-routing.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ChartsModule, ThemeService } from 'ng2-charts';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { NgxMapboxGLModule } from 'ngx-mapbox-gl';
import { AppComponent } from './app.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { TodoComponent } from './apps/todo-list/todo/todo.component';
import { TodoRtlComponent } from './apps/todo-list/todo-rtl/todo-rtl.component';
import { TodoListComponent } from './apps/todo-list/todo-list.component';
import { SpinnerComponent } from './shared/spinner/spinner.component';
import { ContentAnimateDirective } from './shared/directives/content-animate.directive';
import {NgSelectModule} from '@ng-select/ng-select';
import { EmployeeComponent } from './employee/employee.component';
import { PatientComponent } from './patient/patient.component';
import { FeeComponent } from './fee/fee.component';
import { PatientEntryComponent } from './patient-entry/patient-entry.component';
import { InventoryComponent } from './inventory/inventory.component';
import { RoomComponent } from './room/room.component';
import { PatientStatusComponent } from './patient-status/patient-status.component';
import { DoctorPatientComponent } from './doctor-patient/doctor-patient.component';
import { StatusComponent } from './status/status.component';

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/');
}

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DashboardComponent,
    TodoComponent,
    TodoListComponent,
    TodoRtlComponent,
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
        ChartsModule,
        CarouselModule,
        NgxMapboxGLModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        }),
        NgSelectModule
    ],
  providers: [ThemeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
