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

  uploadTopicAttachment(form) {
    console.log(form);
    const headers = new HttpHeaders();
    console.log('headers appending');
    headers.append('Content-Type', 'multipart/form-data');
    console.log('setting heders');
    headers.set('Accept', 'application/json');
    console.log('going to api');
    return this.http.post(environment.apiUrl + 'uploadDoc', form, { headers: headers });

  }

  saveTopic(topic: Topic): Observable<any> {
    return this.http.post(environment.apiUrl + 'topics/topic', topic);
  }
  getTopics(): Observable<any> {
    return this.http.get(environment.apiUrl + 'topics/all');
  }

  updateTopic(topic: any) {
    return this.http.put<any>(environment.apiUrl + 'topics/' + topic.id, topic, httpOptions);
  }

  getTopicAtt(): Observable<any> {
    return this.http.get(environment.apiUrl + 'TopicAtt');

  }

  DownloadAtt(){
  }

}
