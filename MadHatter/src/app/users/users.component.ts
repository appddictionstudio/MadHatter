import { Component, OnInit, OnChanges } from '@angular/core';
import { UserService } from '../services/user.service';
import { CdkDetailRowDirective } from './cdk-detail-row.directive';
import { DataSource } from '@angular/cdk/collections';
import { SelectionModel } from '@angular/cdk/collections';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { PageEvent, MatDialog } from '@angular/material';
import { Router, RouterModule } from '@angular/router';

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
  resetpassword = [null];
  currentPage = 0;
  pageSize = 10;

  constructor(
    private apiU: UserService,
    private router: Router,
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
    if (this.resetpassword[r.id] === true) {
      r.passReset = 'Y';
    }
    // console.log(r);
    this.apiU.updateUser(r).subscribe(data => {
      this.snotifyService.success('User Updated', {
        timeout: 2000,
        showProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
    });
    if (this.resetpassword[r.id] === true) {
      r.password = 'M@ddH@tt3r';
      this.apiU.updateUserPass(r).subscribe(res => {

      });
    }
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
        showProgressBar: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
    } else {
      const stringToSplit = this.name;
      this.password = 'M@ddH@tt3r';
      this.nm = stringToSplit.split(' ');
      this.username = this.nm[0].charAt(0) + this.nm[1];
      this.obj = {email: this.email, password: this.password, name: this.name, username: this.username, roleName: this.class};
      // console.log(this.obj);
      this.apiU.searchForEmail(this.email).subscribe(data => {
        const user = data as any;
        if (data) {
          // console.log('email already exists');
          // console.log(data);
          this.snotifyService.error('Email is taken, by ' + user.name, {
            timeout: 2000,
            closeOnClick: true,
            showProgressBar: false,
            pauseOnHover: true,
            position: SnotifyPosition.centerBottom
          });
        } else {
          this.assignuser();
        }
      });
    }
  }

  redirecttheuser() {
    this.router.navigateByUrl('/');
  }

  assignuser() {
    this.apiU.searchForUsername(this.username).subscribe(data => {
      if (data) {
        // console.log('username already exists');
        this.i = this.i + 1;
        this.username = this.username + this.i;
        // console.log(this.username);
        this.authenticate();
      }
      this.apiU.signUpUser(this.obj).subscribe(res => {
        this.snotifyService.success('Student Added', {
          timeout: 2000,
          closeOnClick: true,
          showProgressBar: false,
          pauseOnHover: true,
          position: SnotifyPosition.centerBottom
        });
      });
      this.i = 0;
      this.email = null;
      this.name = null;
      this.username = null;
      // console.log(this.obj);
    });
  }
  ngOnChanges() {
    this.isLoading = true;
    this.loadUsers();
    // console.log(this.search);
  }

  pageEventHandler(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadUsers();
  }

  loadUsers() {
    if (this.search) {
      // console.log(this.search);
      this.apiU.searchByNamePage(this.search, this.currentPage, this.pageSize).subscribe( res => {
        this.members = res['content'];
        this.isLoading = false;
      });
    } else {
      this.apiU.getAllUsersPage(this.currentPage, this.pageSize).subscribe(data => {
        this.members = data['content'];
        this.isLoading = false;
      });
    }
  }

}
