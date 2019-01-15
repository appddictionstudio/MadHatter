import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { AppComponent } from '../app.component';
import { Observable } from 'rxjs';

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

  constructor(
    private auth: AuthService,
    private router: Router,
    private appcomp: AppComponent,
  ) { }

  ngOnInit() {
    if (this.auth.isAuthenticated()) {
      this.router.navigateByUrl('/home');
    }
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
    });
  }

  getToken() {
    const token = this.auth.getToken();
  }


}
