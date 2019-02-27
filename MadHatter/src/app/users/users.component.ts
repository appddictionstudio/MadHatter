import { Component, OnInit, OnChanges } from '@angular/core';
import { UserService } from '../services/user.service';
import { CdkDetailRowDirective } from './cdk-detail-row.directive';
import { DataSource } from '@angular/cdk/collections';
import { SelectionModel } from '@angular/cdk/collections';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit, OnChanges {
  email: string;
  name: string;
  password: string;
  username: string;
  obj: any;
  nm: any[];
  class: any;
  i = 0;
  currentUser: any;
  isLoading: boolean;
  selectedMember = [null];
  members: any[];
  isApiDone = false;
  displayedColumns = ['Role', 'Name', 'Email', 'Username'];
  search: any;

  constructor(
    private apiU: UserService,
    private snotifyService: SnotifyService,
    ) { }

  ngOnInit() {
    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
      this.isApiDone = true;
    });
    this.loadUsers();
  }

  userRoleShort(roleName) {
    if (roleName === 'ROLE_ADMIN') {
      return 'Admin';
    } if (roleName === 'ROLE_TEACHER_UI') {
      return 'UI/UX Teacher';
    } if (roleName === 'ROLE_TEACHER_ASD') {
      return 'ASD Teacher';
    } if (roleName === 'ROLE_STUDENT_UI') {
      return 'UI/UX Student';
    } if (roleName === 'ROLE_STUDENT_ASD') {
      return 'ASD Student';
    }
  }

  setRoleId(roleName) {
    if (roleName === 'ROLE_ADMIN') {
      return 1;
    } if (roleName === 'ROLE_TEACHER_UI') {
      return 4;
    } if (roleName === 'ROLE_TEACHER_ASD') {
      return 2;
    } if (roleName === 'ROLE_STUDENT_UI') {
      return 5;
    } if (roleName === 'ROLE_STUDENT_ASD') {
      return 3;
    }
  }

  updateUser(r) {
    r.roles[0].id = this.setRoleId(r.roles[0].name);
    console.log(r);
    this.apiU.updateUser(r).subscribe(data => {
      this.snotifyService.success('User Updated', {
        timeout: 2000,
        closeOnClick: true,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
    });
  }

  getUserRoleAdmin() {
    if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    } else {
      return false;
    }
  }

  authenticate() {
    if (!this.email || !this.name || !this.class) {
      this.snotifyService.error('Please fill out all fields', {
        timeout: 2000,
        closeOnClick: true,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
    } else {
      const stringToSplit = this.name;
      this.password = 'M@ddH@tt3r';
      this.nm = stringToSplit.split(' ');
      this.username = this.nm[0].charAt(0) + this.nm[1];
      this.obj = {email: this.email, password: this.password, name: this.name, username: this.username, roleName: this.class};
      console.log(this.obj);
      this.apiU.searchForEmail(this.email).subscribe(data => {
        const user = data as any;
        if (data) {
          console.log('email already exists');
          console.log(data);
          this.snotifyService.error('Email is taken, by ' + user.name, {
            timeout: 2000,
            closeOnClick: true,
            pauseOnHover: true,
            position: SnotifyPosition.centerBottom
          });
        } else {
          this.assignuser();
        }
      });
    }
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
      this.email = null;
      this.name = null;
      this.username = null;
      console.log(this.obj);
    });
  }
  ngOnChanges() {
    this.isLoading = true;
    this.loadUsers();
    console.log(this.search);
  }
  loadUsers() {
    if (this.search) {
      this.apiU.searchByName(this.search).subscribe( res => {
        this.members = res as any[];
        this.isLoading = false;
      });
    } else {
      this.apiU.getAllUsers().subscribe(data => {
        this.members = data as any[];
        this.isLoading = false;
      });
    }
  }

  proccessRow(r) {
  }

}
