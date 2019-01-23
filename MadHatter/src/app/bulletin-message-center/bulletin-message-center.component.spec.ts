import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BulletinMessageCenterComponent } from './bulletin-message-center.component';

describe('BulletinMessageCenterComponent', () => {
  let component: BulletinMessageCenterComponent;
  let fixture: ComponentFixture<BulletinMessageCenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BulletinMessageCenterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BulletinMessageCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
