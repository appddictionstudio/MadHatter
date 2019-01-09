import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  userLogIn: boolean;
  usernameOrEmail: string;
  password: string;
  obj: any;
  // tslint:disable-next-line:max-line-length
  testToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNTQ3MDQ1ODYyLCJleHAiOjE1NDcwODkwNjIsInVzZXJQcm9maWxlIjp7ImlkIjoyLCJuYW1lIjoiRHlsYW4iLCJ1c2VyTmFtZSI6ImRjcmFpZyIsImVtYWlsIjoiZGNyYWlnQGFwcGRkaWN0aW9uc3R1ZGlvLmNvbSIsInJvbGUiOiJST0xFX0FETUlOIn19.2TAe-tXc2tivzF7zifPNNyj5rqZH-WPWExTiN_Wv9-kFku978u2DSPtMZW2jFiJ7WWU6cdyT2o7-5ljUnpsElA';

  constructor(
    private auth: AuthService,
    private router: Router,
    private LoggedIn: AppComponent,
  ) { }

  ngOnInit() {
    if (this.auth.isAuthenticated()) {
      this.router.navigateByUrl('/');
    }
    // this.LoggedIn.displayNavBar(false);
  }

  // setUserLogIn() {
  //   this.LoggedIn.displayNavBar(false);
  //   console.log(`logged in`);
  // }

  authenticate() {
    this.obj = { 'usernameOrEmail': this.usernameOrEmail,
    'password': this.password} ;
    this.auth.token(this.obj).subscribe(result => {
      // this.auth.setToken(this.testToken);
      this.auth.setToken(result['accessToken']);
      this.auth.isAuthenticated();
      this.auth.redirectToRequestedView();
    });
  }

  getToken() {
    const token = this.auth.getToken();
  }


}
