import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { AuthService } from '../services/auth.service';
import { NgbModalConfig, NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../services/user.service';
import { SnotifyService, SnotifyPosition, SnotifyToastConfig } from 'ng-snotify';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit, OnChanges {

  @Input() isAuth: boolean;

  currentUser: any;
  isApiDone = false;
  PasswordInputChange2: string;
  PasswordInputChange1: string;
  content: any;
  fullUser: any;

  constructor(
    private auth: AuthService,
    private router: Router,
    private appcomp: AppComponent,
    private sanitizer: DomSanitizer,
    private modalService: NgbModal,
    private userService: UserService,
    private snotifyService: SnotifyService,
  ) { }

  ngOnInit() {
    // this.appcomp.intercept(this.auth.isAuthenticated(), null);
    this.userService.getUser().subscribe(data => {
      this.currentUser = data;
      console.log(this.currentUser);
      if (!this.currentUser) {
        this.auth.destroyToken();
        location.reload();
     }
      this.userService.searchForEmail(this.currentUser.email).subscribe(res => {
        this.fullUser = res as any;
        if (this.fullUser.passReset === 'Y') {
          this.snotifyService.info('Your password needs to be changed', {
            buttons: [
              {text: 'Change Now', action: (toast) => {this.open(this.content); this.snotifyService.remove(toast.id); }},
            ],
            position: SnotifyPosition.centerBottom,
            closeOnClick: false,
            timeout: 0,
          });
        }
      });
      this.isApiDone = true;
    },
    error => {
      this.auth.destroyToken();
      location.reload();
    });
  }

  getCurrentUser(c) {
    if (this.content === c) {
      return this.currentUser.name;
    } else {
    this.content = c;
    return this.currentUser.name;
    }
  }

  open(content) {
    this.modalService.open(content);
  }

  close() {
    this.modalService.dismissAll();
  }

  ngOnChanges(changes: SimpleChanges) {
  }

  getUserRoleStudent() {
    if (this.currentUser) {
      if (this.currentUser.role === 'ROLE_STUDENT_ASD') {
        return true;
      }
      if (this.currentUser.role === 'ROLE_STUDENT_UI') {
        return true;
      }
    } else {
      return false;
    }

  }

  getUserRoleInstructor() {
    if (this.currentUser) {
      if (this.currentUser.role === 'ROLE_TEACHER_ASD') {
        return true;
      } if (this.currentUser.role === 'ROLE_TEACHER_UI') {
        return true;
      } if (this.currentUser.role === 'ROLE_ADMIN') {
        return true;
      }
    } else {
      return false;
    }
  }

  getUserRoleAdmin() {
    if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    }
  }

  isAuthenticated() {
    this.isAuth = this.auth.isAuthenticated();
  }

  destroyToken() {
    this.appcomp.destroyToken();
  }

  updateUserPassword() {
    if (this.PasswordInputChange2 === this.PasswordInputChange1) {
      this.fullUser.password = this.PasswordInputChange1;
      this.fullUser.passReset = 'N';
      console.log(this.fullUser);
      this.userService.updateUserPass(this.fullUser).subscribe(data => {
        this.close();
        this.snotifyService.success('Password updated', {
          position: SnotifyPosition.centerBottom,
          timeout: 1000,
        });
      });
    } else {
      this.snotifyService.error('Two fields should match', {
        position: SnotifyPosition.centerBottom,
        timeout: 1000,
      });
    }
  }

}
