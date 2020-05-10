import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureDetailPageComponent } from './adventure-detail-page.component';

describe('AdventureDetailPageComponent', () => {
  let component: AdventureDetailPageComponent;
  let fixture: ComponentFixture<AdventureDetailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdventureDetailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
