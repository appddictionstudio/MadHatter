import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse,
} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnChanges {
  title = 'MadHatter';
  isAuth: boolean;

  constructor(
    private router: Router,
    private auth: AuthService,
  ) { }

  ngOnInit() {
    this.auth.getCurrentUser();
    this.isAuth = this.auth.isAuthenticated();
    console.log(this.isAuth);
    if (!this.isAuth) {
      this.auth.destroyToken();
    } else {

    }
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  destroyToken() {
    this.auth.destroyToken();
    this.router.navigateByUrl('/login');
    this.isAuth = false;
  }
  userLoggedIn(auth) {
    this.isAuth = auth;
  }

  hideNavBar() {
    this.isAuth = false;
  }
}
