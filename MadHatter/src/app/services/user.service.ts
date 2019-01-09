import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  getAllUsers(): Observable<any> {
    return this.http.get(environment.apiUrl + 'api/users');
  }

  searchByName(search: any) {
    return this.http.get(environment.apiUrl + 'SearchUser/' + search);
  }
  getCurrentUser(): Observable<any> {
    return this.http.get(environment.apiUrl + '/api/users/user-profile');

  }
}
