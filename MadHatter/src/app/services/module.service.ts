import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BootcampModule } from '../models/Bootcamp';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModuleService {

  constructor(
    private http: HttpClient,
  ) { }

  getModule(): Observable<any>  {
    return this.http.get(environment.apiUrl + 'api/modules');
  }

  getTopicsById(modId): Observable<any> {
    return  this.http.get(environment.apiUrl + 'topics/getByModId/' + modId );
  }

  getAllTopics(): Observable<any> {
    return  this.http.get(environment.apiUrl + 'topics');
  }

  getModById(id: number) {
    return this.http.get(environment.apiUrl + 'api/modules/' + id);
  }

}
