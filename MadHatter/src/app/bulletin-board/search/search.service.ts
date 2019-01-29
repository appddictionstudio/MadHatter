import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  constructor(private http: HttpClient) {}

  searchBulletinPost(search: any) {
    console.log(search);
    return this.http.get(environment.apiUrl + 'SearchBltBrdPost/' + search);
  }
    searchBulletinTopic(topic: any) {
    return this.http.get(environment.apiUrl + 'search/findByTopicContainingIgnoreCase?searchTopic=' + topic);
  }

  searchBulletinPostAndTopics(text: any, topic: any) {
    return this.http.get(environment.apiUrl +
      '/search/findByTextContainingIgnoreCaseAndTopicContainingIgnoreCase' + '?text=' + text + '&topic=' + topic);
  }


}
