import { EmployeeProfileModule } from './employee-profile.module';

describe('EmployeeProfileModule', () => {
  let employeeProfileModule: EmployeeProfileModule;

  beforeEach(() => {
    employeeProfileModule = new EmployeeProfileModule();
  });

  it('should create an instance', () => {
    expect(employeeProfileModule).toBeTruthy();
  });
});
