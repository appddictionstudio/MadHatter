import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnChanges {

  @Input() isAuth: boolean;

  constructor(
    private auth: AuthService,
    private router: Router,
    private appcomp: AppComponent,
  ) { }

  ngOnInit() {
    this.isAuth = this.auth.isAuthenticated();
  }

  ngOnChanges(changes: SimpleChanges) {
    // this.isAuth = this.auth.isAuthenticated();
    console.log('changes');
    console.log(changes);
  }

  isAuthenticated() {
    this.isAuth = this.auth.isAuthenticated();
  }

  destroyToken() {
    this.appcomp.destroyToken();
  }

}
