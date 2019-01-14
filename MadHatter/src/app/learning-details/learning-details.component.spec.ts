import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LearningDetailsComponent } from './learning-details.component';

describe('LearningDetailsComponent', () => {
  let component: LearningDetailsComponent;
  let fixture: ComponentFixture<LearningDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LearningDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LearningDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
