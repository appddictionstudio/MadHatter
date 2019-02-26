import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  email: string;
  name: string;
  password: string;
  username: string;
  obj: any;
  nm: any[];
  class: any;
  i = 0;

  constructor(
    private apiU: UserService,
    private snotifyService: SnotifyService,
    ) { }

  ngOnInit() {
  }

  authenticate() {
    const stringToSplit = this.name;
    this.password = 'M@ddH@tt3r';
    this.nm = stringToSplit.split(' ');
    this.username = this.nm[0].charAt(0) + this.nm[1];
    this.obj = {email: this.email, password: this.password, name: this.name, username: this.username, roleName: this.class};
    console.log(this.obj);
    this.apiU.searchForEmail(this.email).subscribe(data => {
    });
    this.assignuser();
  }

  assignuser() {
    this.apiU.searchForUsername(this.username).subscribe(data => {
      if (data) {
        console.log('username already exists');
        this.i = this.i + 1;
        this.username = this.username + this.i;
        console.log(this.username);
        this.authenticate();
      }
      this.apiU.signUpUser(this.obj).subscribe(res => {
        this.snotifyService.success('Student Added', {
          timeout: 2000,
          closeOnClick: true,
          pauseOnHover: true,
          position: SnotifyPosition.centerBottom
        });
      });
      this.i = 0;
      console.log(this.obj);
    });
  }

}
