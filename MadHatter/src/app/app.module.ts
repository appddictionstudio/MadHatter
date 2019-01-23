import { BrowserModule } from '@angular/platform-browser';
import { ActivityComponent } from './bulletin-board/newsfeed/activity/activity.component';
import { SearchComponent } from './bulletin-board/search/search/search.component';
import { BulletinBoardComponent } from './bulletin-board/bulletin-board.component';
import { BulletinFavsComponent } from './bulletin-favs/bulletin-favs.component';
import { BulletinMessageCenterComponent } from './bulletin-message-center/bulletin-message-center.component';
import { NgModule } from '@angular/core';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LearningComponent } from './learning/learning.component';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BaseInterceptor } from './interceptors/BaseInterceptor';
import { NavbarComponent } from './navbar/navbar.component';
import { VideosComponent } from './videos/videos.component';
import { MemberDirectoryComponent } from './member-directory/member-directory.component';
import { SignupComponent } from './signup/signup.component';
import { YoutubePlayerModule } from 'ngx-youtube-player/ngx-youtube-player';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { SearchByNamePipe } from './member-directory/search-by-name.pipe';
import { MatDialogModule } from '@angular/material';
import { CommonModule } from '@angular/common';
import { ErrorHandler } from './services/error_handler';
import { RequestInterceptor } from './services/http_interceptor';
import {
  MatButtonModule,
  MatMenuModule,
  MatCheckboxModule,
  MatToolbarModule,
  MatIconModule,
  MatFormFieldModule,
  MatSlideToggleModule,
  MatOptionModule,
  MatButtonToggleModule,
  MatSelectModule,
  MatInputModule,
  MatDatepickerModule,
  MatNativeDateModule,
} from '@angular/material';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatChipsModule } from '@angular/material/chips';
import { LearningDetailsComponent } from './learning-details/learning-details.component';
import { AdminComponent } from './admin/admin.component';
import { LaunchDownloadsModalComponent } from './launch-downloads-modal/launch-downloads-modal.component';
import { MatListModule } from '@angular/material/list';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
// import { NgbdModalBasic } from './modal-basic';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LearningComponent,
    NavbarComponent,
    VideosComponent,
    MemberDirectoryComponent,
    SignupComponent,
    SearchByNamePipe,
    LearningDetailsComponent,
    AdminComponent,
    LaunchDownloadsModalComponent,
    BulletinMessageCenterComponent,
    BulletinFavsComponent,
    BulletinBoardComponent,
    SearchComponent,
    ActivityComponent,
    // NgbdModalBasic
  ],
  imports: [
    BrowserModule,
    SnotifyModule,
    AppRoutingModule,
    AngularFontAwesomeModule,
    MatTabsModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule ,
    MatCardModule,
    HttpClientModule,
    YoutubePlayerModule,
    MatCheckboxModule,
    MatButtonModule,
    MatSlideToggleModule,
    MatMenuModule,
    MatToolbarModule,
    MatPaginatorModule,
    MatIconModule,
    MatButtonToggleModule,
    MatFormFieldModule,
    MatExpansionModule,
    MatOptionModule,
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatChipsModule,
    MatFormFieldModule,
    MatListModule,
    MatDialogModule,
    CommonModule,
    NgbModule.forRoot(),
    MatProgressSpinnerModule,
  ],
  providers: [
    RequestInterceptor,
    ErrorHandler,
    {
      provide: 'SnotifyToastConfig',
      useValue: ToastDefaults
    },
    AppComponent,
    SnotifyService,
    { provide: HTTP_INTERCEPTORS, useClass: BaseInterceptor, multi: true }],
  exports: [SearchByNamePipe,
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    FormsModule,
    MatFormFieldModule,
    ReactiveFormsModule, ],
  bootstrap: [AppComponent]
})
export class AppModule {}
