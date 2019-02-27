import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, Subject } from 'rxjs';
import { Users } from '../models/Users';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,
    private api: AuthService) { }

  // private user: Users = new Users();
  private user: any;
  private id: number;
  private userSubject = new Subject<Users>();


  getAllUsers(): Observable<any> {
    return this.http.get(environment.apiUrl + 'api/users');
  }

  updateUser(user): Observable<any> {
    return this.http.put(environment.apiUrl + 'api/auth/updateUser', user);
  }

  updateUserPass(user): Observable<any> {
    return this.http.put(environment.apiUrl + 'api/auth/updateUser/password', user);
  }

  searchForUsername(search) {
    return this.http.get(environment.apiUrl + 'api/users/SearchUsername/' + search);
  }

  searchForEmail(search) {
    return this.http.get(environment.apiUrl + 'api/users/SearchEmail/' + search);
  }

  signUpUser(obj) {
    return this.http.post(environment.apiUrl + 'api/auth/signup', obj);
  }

  searchByName(search: any) {
    return this.http.get(environment.apiUrl + 'api/users/SearchUser/' + search);
  }

  // getUser(): Observable<Users> {
  //   this.userSubject.next(this.user);
  //   return this.userSubject.asObservable();
  // }

  getUser(): Observable<any> {
      const obs$ = this.api.getCurrentUser();
      obs$.subscribe(data => {
        this.user = data;
      });
      return obs$;
    // } else {
    //   return new Observable((observer) => {
    //     observer.next(this.user);
    //     observer.complete();
    //   });

    }
  // }

  userHasRole(roleName: string) {
    if (this.user.roles && this.user.roles.length > 0) {
      let retVal = false;
      this.user.roles.some(role => {
        if (role.nm.toUpperCase() === roleName.toUpperCase()) {
          retVal = true;
          return false;
        }
      });

}
  }
}
