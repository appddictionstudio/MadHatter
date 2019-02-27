import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { AuthService } from '../services/auth.service';
import {NgbModalConfig, NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnChanges {

  @Input() isAuth: boolean;

  currentUser: any;
  isApiDone = false;

  constructor(
    private auth: AuthService,
    private router: Router,
    private appcomp: AppComponent,
    private modalService: NgbModal,
    private userService: UserService
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
      this.isApiDone = true;
    },
    error => {
      this.auth.destroyToken();
      location.reload();
    });
  }

  open(content, t, m) {
    this.modalService.open(content, {ariaLabelledBy: 'ngbd-modal-confirm'}).result.then((result) => {
      // this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      // this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
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

}
