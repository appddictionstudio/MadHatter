import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private redirectLocation: string;

  constructor(
    private http: HttpClient,
    private router: Router) { }

  token(obj) {
    // console.log(obj);
    return this.http.post(environment.apiUrl + 'api/auth/signin', obj);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('/');
  }

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  destroyToken() {
    localStorage.removeItem('token');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  redirectToLogin() {
    this.router.navigateByUrl('#');
  }

  isAuthenticated() {
    return this.getToken() ? true : false;
  }

  setRedirectLocation() {
    this.redirectLocation = '#';
  }

  redirectToRequestedView() {
    this.router.navigateByUrl(this.redirectLocation);
  }

  getRedirectLocation() {
    return this.redirectLocation;
  }

  getCurrentUser() {
    return this.http.get(environment.apiUrl + 'api/users/user-profile');
  }

}
