import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppModule } from '../app.module';

// import { BulletinBoardRoutingModule } from './bulletin-board-routing.module';
// import { BulletinBoardComponent } from './bulletin-board.component';
import { SharedModule } from '../shared/shared.module';
// import { ActivityComponent } from './newsfeed/activity/activity.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
// import { BulletinFavsComponent } from '../bulletin-favs/bulletin-favs.component';
// import { SearchComponent } from './search/search/search.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { BaseInterceptor } from '../interceptors/BaseInterceptor';

@NgModule({
  // declarations: [BulletinFavsComponent, BulletinBoardComponent, ActivityComponent, SearchComponent],
  imports: [
    NgbModule,
    AppModule,
    SharedModule,
    // BulletinBoardRoutingModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BaseInterceptor, multi: true }
  ]

})
export class BulletinBoardModule { }
