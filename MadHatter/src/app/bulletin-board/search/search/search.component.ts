import { BulletinBoardComponent } from './../../bulletin-board.component';
import { BulletinBoardService } from './../../../services/bulletin-board.service';
import { HttpClient } from '@angular/common/http';
import { Person } from 'src/app/models/Person';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { SearchService } from '../search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

// showX = 'false';
  name = 'Angular';

  currPageNumber = 0;
  postFilter = new FormControl('');
  favsFilter = new FormControl('');
  titleFilter = new FormControl('');
  searchCriteria = new FormControl('');
  attachmentFilter = new FormControl('');


  searchAllPostsText: any;

  posts: any[] = [];

  searchText: any;
  searchTopic: any;

  filterValues = {
    title: '',
    post: '',
    favs: '',
    attachment: '',
  };


  constructor(private http: HttpClient,
    private api: SearchService,
    private bulletinService: BulletinBoardService
    ) { }

  ngOnInit() {

    this.bulletinService.getAllBulletinPost().subscribe(data => {
      this.posts = data as any[];
      console.log('here ar posts');
      console.log(this.posts);
    });

  }


  searchBulletinPosts(searchText) {
    this.api.searchBulletinPost(searchText).subscribe(data => {
      // this.posts = data as any[];
    });
  }

  // searchBulletinTopics(searchTopic) {
  //   this.api.searchBulletinTopic(searchTopic).subscribe(data => {
  //     this.posts = data as any[];
  //   });
  // }



  searchBulletinPostAndTopics(text: any , topic: any) {
    this.api.searchBulletinPostAndTopics(text, topic).subscribe(data => {
      this.posts = data as any[];
    });
  }
}
