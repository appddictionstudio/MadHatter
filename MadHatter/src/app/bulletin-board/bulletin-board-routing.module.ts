import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BulletinBoardComponent } from './bulletin-board.component';

const routes: Routes = [
  {
    path: '',
    component: BulletinBoardComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BulletinBoardRoutingModule { }
