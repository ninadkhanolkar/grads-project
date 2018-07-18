import { TestBed, inject } from '@angular/core/testing';

import { AllSkillsService } from './all-skills.service';

describe('AllSkillsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AllSkillsService]
    });
  });

  it('should be created', inject([AllSkillsService], (service: AllSkillsService) => {
    expect(service).toBeTruthy();
  }));
});
