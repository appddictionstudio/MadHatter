import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LaunchDownloadsModalComponent } from './launch-downloads-modal.component';

describe('LaunchDownloadsModalComponent', () => {
  let component: LaunchDownloadsModalComponent;
  let fixture: ComponentFixture<LaunchDownloadsModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LaunchDownloadsModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LaunchDownloadsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
