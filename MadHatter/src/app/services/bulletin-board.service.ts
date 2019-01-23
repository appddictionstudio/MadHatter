import { Activities } from './../bulletin-board/newsfeed/activity/activity.component';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BulletinMessageCenter } from '../models/bulletinBoard';
import { environment } from 'src/environments/environment';
import { Observable, observable } from 'rxjs';
import { BulletinBoardFavorite } from '../models/BulletinBoardFavorite';
import { BulletinBoardPostComment } from '../models/BulletinBoardPostComment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})

export class BulletinBoardService {

  constructor(private http: HttpClient) { }

  // showX = false;

  createBulletinPost(bulletinMessageCenter: BulletinMessageCenter): Observable<any> {
    return this.http.post(environment.apiUrl + 'BltBrdPost', bulletinMessageCenter);
  }

  createPostComment(bulletinBoardPostComment: BulletinBoardPostComment, postId): Observable<any> {
    return this.http.post(environment.apiUrl + 'BltBrdPostComment/' + postId, bulletinBoardPostComment);
  }
  createBulletinPostLike(id: number): Observable<any> {
    return this.http.post<any>(environment.apiUrl + 'BltBrdPost/like/' + id, httpOptions);
  }

  getPostlikesByEodTechId(id: number): Observable<any> {
    return this.http.get<any>(environment.apiUrl + 'BltBrdAct/getLikesByEodTechId/' + id, httpOptions);
  }

  getAllBulletinPost() {
    return this.http.get(environment.apiUrl + 'BltBrdPost');
  }

  getAllActivity(): Observable<any> {
    return this.http.get<any>(environment.apiUrl + 'getAllBltBrdAct');
  }

  getHotTopicPosts(page: number) {
    return this.http.get(environment.apiUrl + 'BltBrdHotTopic?page=' + page + '&size=3');
  }

  getAllFavorites(): Observable<any> {
    return this.http.get<Observable<BulletinBoardFavorite>>(environment.apiUrl + 'bltBrdFav');
  }
  getAllPostsText(): Observable<any> {
    return this.http.get<any>(environment.apiUrl + 'bltBrdPosts');
  }
  getFavsPostsTextsById(id: number): Observable<any> {
    return this.http.get<any>(environment.apiUrl + 'bltBrdFavs/' + `${id}` + '/post');
  }
  postFavorite(favorite: BulletinBoardFavorite): Observable<any> {
    return this.http.post<Observable<BulletinBoardFavorite>>(environment.apiUrl + '/bltBrdFav', favorite, httpOptions);
  }
  deleteFavorite(id: number): Observable<any> {
    return this.http.delete<Observable<BulletinBoardFavorite>>(environment.apiUrl + 'bltBrdFav/' + id, httpOptions);
  }


  uploadBulletinBoardAttachment(form) {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.set('Accept', 'application/json');
    return this.http.post(environment.apiUrl + 'uploadDoc', form, { headers: headers });

  }

}
