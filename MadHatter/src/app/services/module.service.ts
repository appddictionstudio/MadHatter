import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
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

  getModuleByBootcamp(bootcamp): Observable<any>  {
    return this.http.get(environment.apiUrl + 'api/modules/bootcamp', bootcamp);
  }

  getModuleById(id): Observable<any>  {
    return this.http.get(environment.apiUrl + 'api/modules/' + id);
  }

  getTopicsById(modId): Observable<any> {
    return  this.http.get(environment.apiUrl + 'topics/getByModId/' + modId );
  }

  getTopicsByAll(): Observable<any> {
    return  this.http.get(environment.apiUrl + 'topics/all');
  }

  getAllTopics(): Observable<any> {
    return  this.http.get(environment.apiUrl + 'topics');
  }

  getModById(id: number) {
    return this.http.get(environment.apiUrl + 'api/modules/' + id);
  }

  getResources(id: number) {
    return this.http.get(environment.apiUrl + 'resources/getResourceByModId/' + id);
  }

  // toggleHidden(topic): Observable<any> {
  //   console.log('this api is going ot start');
  //   return this.http.get(environment.apiUrl + 'hide/' + topic.id, topic);
  // }
  uploadModAttachment(form) {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.set('Accept', 'application/json');
    return this.http.post(environment.apiUrl + 'uploadMDoc', form, { headers: headers });

  }

  updateMod(mod: any) {
    return this.http.put(environment.apiUrl + 'api/modules/update/' + mod.id, mod);
  }

}
