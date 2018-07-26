import { TestBed, async, inject } from '@angular/core/testing';

import { AdminEmployeeAccessGuard } from './admin-employee-access.guard';

describe('AdminEmployeeAccessGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminEmployeeAccessGuard]
    });
  });

  it('should ...', inject([AdminEmployeeAccessGuard], (guard: AdminEmployeeAccessGuard) => {
    expect(guard).toBeTruthy();
  }));
});
