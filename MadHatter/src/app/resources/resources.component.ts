import { Component, OnInit } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { UserService } from '../services/user.service';
import { flattenStyles } from '@angular/platform-browser/src/dom/dom_renderer';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.scss']
})
export class ResourcesComponent implements OnInit {

  modules: any;
  resources: any;
  resourceCheckByModId = [null];
  resourceCheck = [null];
  currentUser: any;
  currentUserRole: any;

  constructor(
    private api: ModuleService,
    private Uapi: UserService,
  ) { }

  ngOnInit() {
    this.api.getModule().subscribe(data => {
      console.log(data);
      this.modules = data as any;
      this.Uapi.getUser().subscribe(res => {
        this.currentUser = res as any;
        this.setBootcampRole();
      });
    });
  }

  setBootcampRole() {
    if (this.currentUser.role.endsWith('ASD')) {
      this.currentUserRole = 'SD';
      console.log(this.currentUserRole);
    } if (this.currentUser.role.endsWith('UI')) {
      this.currentUserRole = 'UI';
      console.log(this.currentUserRole);
    } if (this.currentUser.role.endsWith('ADMIN')) {
      this.currentUserRole = 'ADMIN';
      console.log(this.currentUserRole);
    } else {
      console.log('something went wrong');
      console.log(this.currentUser);
    }
  }

  checkForResources(mod) {
    if (!this.resourceCheckByModId[mod.id]) {
      console.log(mod.resources);
      if (this.currentUserRole === mod.bootcamp || this.currentUserRole === 'ADMIN') {
        if (mod.resources.length > 0) {
          this.resourceCheck[mod.id] = true;
          return this.resourceCheck;
        } else {
          this.resourceCheck[mod.id] = true;
          return false;
        }
      } else {
        return false;
      }
    } else {
      return this.resourceCheck[mod.id];
    }
  }

}
