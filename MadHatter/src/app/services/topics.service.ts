import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Topic } from '../models/Topic';
import { Observable } from 'rxjs';
import { TopicAtt } from '../models/TopicAtt';

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
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.set('Accept', 'application/json');
    return this.http.post(environment.apiUrl + 'uploadDoc', form, { headers: headers });

  }

  saveTopic(topic: Topic): Observable<any> {
    return this.http.post(environment.apiUrl + 'topics/topic', topic);
  }
  getTopics(): Observable<any> {
    return this.http.get(environment.apiUrl + 'topics/all');
  }

  getTopicsById(id): Observable<any> {
    return this.http.get(environment.apiUrl + 'topics/FindById/' + id);
  }

  getTopicsByModId(modId): Observable<any> {
    return this.http.get(environment.apiUrl + 'topics/getByModId/' + modId);
  }

  updateTopic(topic: any) {
    // console.log(topic);
    return this.http.put(environment.apiUrl + 'topics/' + topic.id, topic);
  }

  getTopicAtt(): Observable<any> {
    return this.http.get(environment.apiUrl + 'TopicAtt');
  }

  updateTopicAtt(a): Observable<any> {
    return this.http.put(environment.apiUrl + 'topicAttUpdate', a);
  }

  DownloadAtt(attachmentId: number) {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/octet-stream');
    headers.set('Accept', 'text/plain');
    return this.http.get(environment.apiUrl + 'TopicAtt/downloadDoc/' + attachmentId,
    { headers: headers, responseType: 'blob', observe: 'response' });
  }

  getAttachments(): Observable<any> {
    return this.http.get(environment.apiUrl + 'att');
  }

  deleteTopicAtt(id: number): Observable<any> {
    return this.http.delete<Observable<TopicAtt>>(environment.apiUrl + 'TopicAtt/' + id, httpOptions );
  }

  getTopicAttById(topicId): Observable<any> {
    return this.http.get(environment.apiUrl + 'topicatt/' + topicId);
  }
}
