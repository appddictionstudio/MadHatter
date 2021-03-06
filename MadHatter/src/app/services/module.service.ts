import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BootcampModule } from '../models/Bootcamp';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModuleService {
  editModule = false;

  constructor(
    private http: HttpClient,
  ) { }

  setModuleToEdit() {
    this.editModule = true;
  }

  checkForEdit() {
    if (this.editModule) {
      this.editModule = false;
      return true;
    } else {
      return false;
    }
  }

  getModule(): Observable<any>  {
    return this.http.get(environment.apiUrl + 'api/modules');
  }

  getModuleByBootcamp(bootcamp): Observable<any>  {
    return this.http.get(environment.apiUrl + 'api/modules/bootcamp/' + bootcamp);
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

  removeResources(id: number) {
    return this.http.delete(environment.apiUrl + 'resources/removeResource/' + id);
  }

  getAllResources() {
    return this.http.get(environment.apiUrl + 'resources/getAllResource');
  }

  setResources(id: number, resource: any) {
    return this.http.post(environment.apiUrl + 'resources/postResourceByModId/' + id, resource);
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

  removeModAttachment(id) {
    return this.http.delete(environment.apiUrl + 'ModAtt/remove/' + id);
  }

  updateMod(mod: any) {
    return this.http.put(environment.apiUrl + 'api/modules/update/' + mod.id, mod);
  }

  returnStudentModsUI() {
    return this.http.get(environment.apiUrl + 'studentModsUI');
  }

  returnStudentModsASD() {
    return this.http.get(environment.apiUrl + 'studentModsASD');
  }

  DownloadMod(modattid) {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/octet-stream');
    headers.set('Accept', 'text/plain');
    return this.http.get(environment.apiUrl + 'ModAtt/downloadDoc/' + modattid,
    { headers: headers, responseType: 'blob', observe: 'response' });
  }

  }

