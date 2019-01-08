import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  name: string;
  email: string;
  username: string;
  password: string;

  constructor() { }

  ngOnInit() {
  }

}
