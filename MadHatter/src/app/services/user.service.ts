import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, Subject } from 'rxjs';
import { Users } from '../models/Users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  private user: Users = new Users();

  private userSubject = new Subject<Users>();


  getAllUsers(): Observable<any> {
    return this.http.get(environment.apiUrl + 'api/users');
  }

  searchByName(search: any) {
    return this.http.get(environment.apiUrl + 'api/users/SearchUser/' + search);
  }
  getCurrentUser(): Observable<Users> {
    this.userSubject.next(this.user);
    return this.userSubject.asObservable();

  }
  // getUser(): Observable<Users> {
  //   this.userSubject.next(this.user);
  //   return this.userSubject.asObservable();
  // }
}
