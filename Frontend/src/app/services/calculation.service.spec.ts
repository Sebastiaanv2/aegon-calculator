import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { CalculationService } from './calculation.service';

describe('CalculationService', () => {
  let service: CalculationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ]
    });
    service = TestBed.inject(CalculationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
