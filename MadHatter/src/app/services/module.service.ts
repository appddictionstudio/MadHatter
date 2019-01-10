import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ModuleService {

  constructor(
    private http: HttpClient,
  ) { }

  getModuleInformation() {
    return this.http.get(environment.apiUrl + 'BootcampList');
  }
  setModuleInformation(obj) {
    return this.http.post(environment.apiUrl + '/api/bootcamp/bootcampSet', obj);
  }
}
