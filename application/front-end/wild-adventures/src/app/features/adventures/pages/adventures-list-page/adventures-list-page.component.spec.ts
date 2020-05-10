import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventuresListPageComponent } from './adventures-list-page.component';

describe('AdventuresListPageComponent', () => {
  let component: AdventuresListPageComponent;
  let fixture: ComponentFixture<AdventuresListPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdventuresListPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventuresListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
