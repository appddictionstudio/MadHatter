import { Component, OnInit, OnChanges } from '@angular/core';
import { UserService } from '../services/user.service';
import { Users } from '../models/Users';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PageEvent, MatDialog } from '@angular/material';

@Component({
  selector: 'app-member-directory',
  templateUrl: './member-directory.component.html',
  styleUrls: ['./member-directory.component.scss']
})
export class MemberDirectoryComponent implements OnInit, OnChanges {
  public searchString: string;

  members: Users[] = [];
  showpeople: boolean;
  isLoading = true;
  searchEnabled = false;
  currentPage = 0;
  pageSize = 10;
  displayedColumns = ['Name', 'Role', 'Email'];

  constructor(
    private api: UserService,
    private router: Router,
    private auth: AuthService,
    ) { }

  ngOnInit() {
  this.loadUsers();
  }

  userRoleShort(roleName) {
    if (roleName === 'ROLE_ADMIN') {
      return 'Admin';
    } if (roleName === 'ROLE_TEACHER_UI') {
      return 'UI / UX Teacher';
    } if (roleName === 'ROLE_TEACHER_ASD') {
      return 'Advanced Software Developer Teacher';
    } if (roleName === 'ROLE_STUDENT_UI') {
      return 'UI / UX Student';
    } if (roleName === 'ROLE_STUDENT_ASD') {
      return 'Advanced Software Developer Student';
    }
  }

  ngOnChanges() {
    this.isLoading = true;
    this.loadUsers();
  }

  loadUsers() {
    this.api.getAllUsersPage(this.currentPage, this.pageSize).subscribe(data => {
      this.members = data['content'];
      this.isLoading = false;
    });
  }

  pageEventHandler(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadUsers();
  }

  searchByName(searchName) {
    if (searchName !== '') {
      this.api.searchByNamePage(searchName, this.currentPage, this.pageSize).subscribe(data =>
        this.members = data['content'] );
        this.showpeople = true;
    } else {
      this.loadUsers();
    }
  }
}
