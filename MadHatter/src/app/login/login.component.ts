import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  userLogIn: boolean;

  constructor(
    private router: Router,
    // private logIn: AppComponent,
  ) { }

  ngOnInit() {
    this.userLogIn = false;
    // this.logIn.userLogInAction(this.userLogIn);
  }

  userLogInAttempt() {
    this.userLogIn = true;
    // this.logIn.userLogInAction(this.userLogIn);
  }
}
