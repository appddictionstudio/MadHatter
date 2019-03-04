import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  currentUser: any;

  constructor(
    private userService: UserService,
    private api: AuthService,
    ) { }

  ngOnInit() {
  }

  deleteToken() {
    this.api.destroyToken();
  }
}
