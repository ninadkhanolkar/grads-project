import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteesListComponent } from './reportees-list.component';

describe('ReporteesListComponent', () => {
  let component: ReporteesListComponent;
  let fixture: ComponentFixture<ReporteesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReporteesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
