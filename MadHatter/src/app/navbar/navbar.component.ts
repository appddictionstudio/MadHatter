import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  userLogIn: boolean;

  constructor() { }

  ngOnInit() {
    this.userLogIn = true;
    console.log(this.userLogIn);
  }

}
