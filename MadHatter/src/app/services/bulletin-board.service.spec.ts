import { TestBed } from '@angular/core/testing';

import { BulletinBoardService } from './bulletin-board.service';

describe('BulletinBoardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BulletinBoardService = TestBed.get(BulletinBoardService);
    expect(service).toBeTruthy();
  });
});
