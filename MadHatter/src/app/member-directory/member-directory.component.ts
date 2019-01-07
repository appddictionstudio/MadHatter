import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Users } from '../models/Users';

@Component({
  selector: 'app-member-directory',
  templateUrl: './member-directory.component.html',
  styleUrls: ['./member-directory.component.scss']
})
export class MemberDirectoryComponent implements OnInit {

  members: Users[] = [];

  constructor(    private api: UserService,
    ) { }

  ngOnInit() {

  }
  loadUsers() {
    this.api.getAllUsers().subscribe(data => {
      this.members = data as any[];
    });
  }
}
