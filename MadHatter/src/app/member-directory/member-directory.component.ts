import { Component, OnInit, OnChanges } from '@angular/core';
import { UserService } from '../services/user.service';
import { Users } from '../models/Users';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-member-directory',
  templateUrl: './member-directory.component.html',
  styleUrls: ['./member-directory.component.scss']
})
export class MemberDirectoryComponent implements OnInit, OnChanges {
  public searchString: string;

  members: Users[] = [];
  displayedColumns: string[] = ['name'];
  showpeople: boolean;
  isLoading = true;
  searchEnabled = false;

  constructor(
    private api: UserService,
    private router: Router,
    private auth: AuthService,
    ) { }

  ngOnInit() {
  this.loadUsers();
    }
  ngOnChanges() {
    this.isLoading = true;
    this.loadUsers();
  }
  loadUsers() {
    this.api.getAllUsers().subscribe(data => {
      this.members = data as any[];
      this.isLoading = false;
    });
  }

  searchByName(searchName) {
    if (searchName !== '') {
      this.api.searchByName(searchName).subscribe(data =>
        this.members = data as any[] );
        this.showpeople = true;
    } else {
      this.loadUsers();
    }
  }
}
