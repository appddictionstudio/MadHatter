import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  userLogIn: boolean;

  constructor(
  ) { }

  ngOnInit() {
  }

  hideNavBar() {
    this.userLogIn = false;
    console.log(this.userLogIn);
  }

  // displayNavBar() {
  //   this.userLogIn = true;
  // }

}
