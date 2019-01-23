import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnChanges {

  @Input() isAuth: boolean;

  currentUser: any;

  constructor(
    private auth: AuthService,
    private router: Router,
    private appcomp: AppComponent,
    private userService: UserService
  ) { }

  ngOnInit() {
    // this.appcomp.intercept(this.auth.isAuthenticated(), null);
    this.userService.getUser().subscribe(data => {
      this.currentUser = data;
      console.log(this.currentUser);
  });
  }

  ngOnChanges(changes: SimpleChanges) {
  }

  getUserRoleStudent() {
    if (this.currentUser.role === 'ROLE_STUDENT_ASD') {
      return true;
    }
    if (this.currentUser.role === 'ROLE_STUDENT_UI') {
      return true;
    }
  }

  getUserRoleInstructor() {
    if (this.currentUser.role === 'ROLE_TEACHER_ASD') {
      return true;
    } if (this.currentUser.role === 'ROLE_TEACHER_UI') {
      return true;
    } if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    }
  }

  getUserRoleAdmin() {
    if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    }
  }

  isAuthenticated() {
    this.isAuth = this.auth.isAuthenticated();
  }

  destroyToken() {
    this.appcomp.destroyToken();
  }

}
