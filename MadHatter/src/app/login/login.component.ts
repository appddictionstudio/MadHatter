import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { AppComponent } from '../app.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnChanges {
  userLogIn: boolean;
  usernameOrEmail: string;
  password: string;
  obj: any;
  isLoggedIn: boolean;
  failedLogin: any;

  constructor(
    private auth: AuthService,
    private router: Router,
    private appcomp: AppComponent,
  ) { }

  ngOnInit() {
      this.auth.destroyToken();
  }

  ngOnChanges(changes: SimpleChanges) {
  }

  authenticate() {
    this.obj = { 'usernameOrEmail': this.usernameOrEmail,
    'password': this.password} ;
    this.auth.token(this.obj).subscribe(result => {
      this.auth.setToken(result['accessToken']);
      const auth = this.auth.isAuthenticated();
      this.auth.redirectToRequestedView();
      this.router.navigateByUrl('/home');
      this.appcomp.userLoggedIn(auth);
      this.isLoggedIn = true;
    },
    error => {
      console.log('failed login');
      this.failedLogin = true;
    },
    () => {
      this.failedLogin = true;
    }
    );
  }

  // getColor() {
  //   if (this.failedLogin) {
  //     return 'rgba(200,0,0,.05)';
  //   }
  // }

  getToken() {
    const token = this.auth.getToken();
  }
}
