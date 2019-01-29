import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { SubmittedAtt } from '../models/SubmittedAtt';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }



  uploadStudentAttachment(form) {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.set('Accept', 'application/json');
    return this.http.post(environment.apiUrl + 'uploadSDoc', form, { headers: headers });

  }

  updateTopicAtt(SubAtt: SubmittedAtt , topicAtt: any): Observable<any> {
    console.log(topicAtt);
    return this.http.put(environment.apiUrl + 'subAtt/' + topicAtt, SubAtt);
  }

  // updateTopicAtt(SubAtt: SubmittedAtt , topicAtt: any): Observable<any> {
  //   console.log(topicAtt);
  //   return this.http.put(environment.apiUrl + 'topicAtt/' + topicAtt, SubAtt);
  // }
}
