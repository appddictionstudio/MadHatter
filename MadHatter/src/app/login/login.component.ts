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
  username: string;
  password: string;

  isAuthenticated = false;

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
    this.auth.token(this.username, this.password).subscribe(result => {
      this.auth.setToken(result['access_token']);
      this.auth.redirectToRequestedView();
    });
  }


}
