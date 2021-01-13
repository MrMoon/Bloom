import {TestBed} from '@angular/core/testing';

import {PatientJoinService} from './patient-join.service';

describe('PatientJoinService', () => {
  let service: PatientJoinService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientJoinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
