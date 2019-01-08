import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(
    private http: HttpClient,
    private router: Router
    ) { }

  getVideos() {
    return this.http.get(environment.apiUrl + '/VideoList', name);
  }
}
