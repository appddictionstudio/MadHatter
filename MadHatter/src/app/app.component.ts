import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

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
    this.isAuth = this.auth.isAuthenticated();
  }

  ngOnChanges(changes: SimpleChanges) {

  }
  destroyToken() {
    this.auth.destroyToken();
    this.router.navigateByUrl('/');
    this.isAuth = this.auth.isAuthenticated();
  }
  userLoggedIn(auth) {
    this.isAuth = auth;
  }
}
