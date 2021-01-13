import {TestBed} from '@angular/core/testing';

import {DoctorPatientServiceService} from './doctor-patient-service.service';

describe('DoctorPatientServiceService', () => {
  let service: DoctorPatientServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoctorPatientServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
