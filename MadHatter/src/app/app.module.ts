import { BrowserModule } from '@angular/platform-browser';
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
import { YoutubePlayerModule } from 'ngx-youtube-player';
import { SearchByNamePipe } from './member-directory/search-by-name.pipe';
import { MatDialogModule } from '@angular/material';
import { CommonModule } from '@angular/common';

import { ErrorHandler } from './services/error_handler';
import { RequestInterceptor } from './services/http_interceptor';
import {
  MatButtonModule,
  MatMenuModule,
  MatToolbarModule,
  MatIconModule,
  MatFormFieldModule,
  MatOptionModule,
  MatSelectModule,
  MatInputModule,
  MatDatepickerModule,
  MatNativeDateModule,
} from '@angular/material';
import { LearningDetailsComponent } from './learning-details/learning-details.component';
import { AdminComponent } from './admin/admin.component';
import { LaunchDownloadsModalComponent } from './launch-downloads-modal/launch-downloads-modal.component';
import { MatListModule } from '@angular/material/list';


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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFontAwesomeModule,
    MatTabsModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule ,
    MatCardModule,
    HttpClientModule,
    YoutubePlayerModule,
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatListModule,
    MatDialogModule,
    CommonModule
  ],
  providers: [
    RequestInterceptor,
    ErrorHandler,
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
