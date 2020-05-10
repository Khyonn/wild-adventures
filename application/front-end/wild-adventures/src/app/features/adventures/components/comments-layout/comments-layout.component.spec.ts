import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentsLayoutComponent } from './comments-layout.component';

describe('CommentsLayoutComponent', () => {
  let component: CommentsLayoutComponent;
  let fixture: ComponentFixture<CommentsLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentsLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentsLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
