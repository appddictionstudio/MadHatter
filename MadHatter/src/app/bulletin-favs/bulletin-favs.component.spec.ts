import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BulletinFavsComponent } from './bulletin-favs.component';

describe('BulletinFavsComponent', () => {
  let component: BulletinFavsComponent;
  let fixture: ComponentFixture<BulletinFavsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BulletinFavsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BulletinFavsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
