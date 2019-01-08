import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Users } from '../models/Users';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private redirectLocation: string;

  constructor(
    private http: HttpClient,
    private router: Router) { }

  //  login(username: string, password: string) {
  //     return this.http.post<any>(environment.apiUrl + 'api/auth/signin', { username, password })
  //         .pipe(map(user => {
  //             // login successful if there's a jwt token in the response
  //             if (user && user.token) {
  //                 // store user details and jwt token in local storage to keep user logged in between page refreshes
  //                 localStorage.setItem('currentUser', JSON.stringify(user));
  //             }

  //             return user;
  //         }));
  // }
  token(usernameOrEmail: string, password: string) {

    const body = {'usernameOrEmail': usernameOrEmail,
    'password': password};

    return this.http.post(environment.apiUrl + '/api/auth/signin', body);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('/login');
  }

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  redirectToLogin() {
    this.router.navigateByUrl('/login');
  }

  isAuthenticated() {
    return this.getToken() ? true : false;
  }

  setRedirectLocation() {
    this.redirectLocation = this.router.url;
  }

  redirectToRequestedView() {
    this.router.navigateByUrl(this.redirectLocation);
  }

  getRedirectLocation() {
    return this.redirectLocation;
  }
}
