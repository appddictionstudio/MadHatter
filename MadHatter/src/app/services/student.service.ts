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

  gradeStudent(submission, id): Observable<any> {
    console.log(submission);
    return this.http.put(environment.apiUrl + 'submittedAtt/' + id, submission);
  }

  getStudentAttempts(id): Observable<any> {
    console.log(id);
    return this.http.get(environment.apiUrl + 'SubmittedAtt/' + id);
  }

  getStudentAttemptsByLesson(id): Observable<any> {
    return this.http.get(environment.apiUrl + 'studentSubmittedAtt/' + id);
  }

  DownloadStudentAtt(attachmentId: number) {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/octet-stream');
    headers.set('Accept', 'text/plain');
    return this.http.get(environment.apiUrl + 'SubmittedAtt/downloadDoc/' + attachmentId,
    { headers: headers, responseType: 'blob', observe: 'response' });
  }

  // updateTopicAtt(SubAtt: SubmittedAtt , topicAtt: any): Observable<any> {
  //   console.log(topicAtt);
  //   return this.http.put(environment.apiUrl + 'topicAtt/' + topicAtt, SubAtt);
  // }
}
