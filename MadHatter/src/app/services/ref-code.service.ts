import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { RefCode } from '../models/RefCode';

@Injectable({
  providedIn: 'root'
})
export class RefCodeService {
  rootUrl = '/RefCode';
  constructor(private http: HttpClient) { }
  getRefcodesByParentName(nm: String) {
    // nm is the name of the parent refCode you are searching for
    return this.http.get(environment.apiUrl + this.rootUrl + '/findByParentNm?nm=' + nm);

  }

  getBltCategory(){
    return this.http.get(environment.apiUrl + 'getBltCategory')
  }

}
