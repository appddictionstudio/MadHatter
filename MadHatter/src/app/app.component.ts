import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'MadHatter';
  isLoggedIn: true;

  displayNavBar(val) {
    console.log(this.isLoggedIn);
    this.isLoggedIn = val;
    console.log(this.isLoggedIn);
  }
}
