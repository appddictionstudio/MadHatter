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
import { FormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { BaseInterceptor } from './interceptors/BaseInterceptor';
import { NavbarComponent } from './navbar/navbar.component';
import { VideosComponent } from './videos/videos.component';
import { MemberDirectoryComponent } from './member-directory/member-directory.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LearningComponent,
    NavbarComponent,
    VideosComponent,
    MemberDirectoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFontAwesomeModule,
    MatTabsModule,
    BrowserAnimationsModule,
    FormsModule,
    MatCardModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BaseInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule {}
