import { Component, OnInit } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { UserService } from '../services/user.service';
import { SnotifyService, SnotifyPosition, SnotifyToastConfig } from 'ng-snotify';
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
    private snotifyService: SnotifyService,
  ) { }

  ngOnInit() {
    this.api.getModule().subscribe(data => {
      this.modules = data as any;
      this.Uapi.getUser().subscribe(res => {
        this.currentUser = res as any;
        this.setBootcampRole();
      });
    });
  }

  getUserRoleInstructor() {
    if (this.currentUser.role === 'ROLE_ADMIN' || this.currentUser.role === 'ROLE_TEACHER_ASD' ||
    this.currentUser.role === 'ROLE_TEACHER_UI') {
      return true;
    } else {
      return false;
    }
  }

  removeResourceLink(resource) {
    this.snotifyService.warning('Are you sure you want to remove this link?', {
      timeout: 100000,
      closeOnClick: true,
      buttons: [
        {text: 'Yes', action: () => this.deleteResourceLink(resource), bold: true },
        {text: 'No', action: null },
      ],
      showProgressBar: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerBottom,
    });
  }

  deleteResourceLink(resource) {
    this.api.removeResources(resource.id).subscribe(res => {
      this.snotifyService.success('Resource removed', {
        position: SnotifyPosition.centerBottom,
        closeOnClick: true,
        timeout: 2000,
      });
      this.ngOnInit();
    });
  }

  setBootcampRole() {
    if (this.currentUser.role.endsWith('ASD')) {
      this.currentUserRole = 'SD';
    } if (this.currentUser.role.endsWith('UI')) {
      this.currentUserRole = 'UI';
    } if (this.currentUser.role.endsWith('ADMIN')) {
      this.currentUserRole = 'ADMIN';
    } else {
    }
  }

  checkForResources(mod) {
    if (!this.resourceCheckByModId[mod.id]) {
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
