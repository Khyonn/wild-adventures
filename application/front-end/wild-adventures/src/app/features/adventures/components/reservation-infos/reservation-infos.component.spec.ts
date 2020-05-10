import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationInfosComponent } from './reservation-infos.component';

describe('ReservationInfosComponent', () => {
  let component: ReservationInfosComponent;
  let fixture: ComponentFixture<ReservationInfosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReservationInfosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationInfosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
