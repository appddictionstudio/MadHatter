import { Component, OnInit, OnChanges } from '@angular/core';
import { UserService } from '../services/user.service';
import { Users } from '../models/Users';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-member-directory',
  templateUrl: './member-directory.component.html',
  styleUrls: ['./member-directory.component.scss']
})
export class MemberDirectoryComponent implements OnInit, OnChanges {
  public searchString: string;

  members: Users[] = [];
  constructor(    private api: UserService,
    ) { }

  ngOnInit() {
this.loadUsers();
  }
  ngOnChanges(){
    this.loadUsers();
  }
  loadUsers() {
    this.api.getAllUsers().subscribe(data => {
      this.members = data as any[];
    });
  }


  searchByName(searchName) {

    this.api.searchByName(searchName).subscribe(data =>
      this.members = data as any[] );
  }
}
