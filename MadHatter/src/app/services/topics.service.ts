import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Topic } from '../models/Topic';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  constructor(private http: HttpClient) { }


  createHidden(setHidden: Topic, modId): Observable<any> {
    return this.http.post<Observable<Topic>>(environment.apiUrl + 'topics/hide/' + modId, setHidden, httpOptions);

  }
}
