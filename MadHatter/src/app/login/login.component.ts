import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { AppComponent } from 'src/app/app.component';
import { AuthService } from '../services/auth.service';

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

  constructor(private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    if (this.auth.isAuthenticated()) {
      this.router.navigateByUrl('/');
    }
  }

  authenticate() {
    this.auth.token(this.username, this.password).subscribe(result => {
      this.auth.setToken(result['access_token']);
      this.auth.redirectToRequestedView();
    });
  }


}
