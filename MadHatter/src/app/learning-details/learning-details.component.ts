import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { TopicsService } from '../services/topics.service';

@Component({
  selector: 'app-learning-details',
  templateUrl: './learning-details.component.html',
  styleUrls: ['./learning-details.component.scss']
})
export class LearningDetailsComponent implements OnInit, OnChanges {

  constructor(private api: ModuleService,
    private apiU: UserService,
    private route: ActivatedRoute,
    private apiT: TopicsService
    ) { }

  topics: Topic[] = [];
  topicHide: Topic = new Topic();
  allTopic: Topic[] = [];
  hide: string;
  currentUser: any;
  modId: any;

  ngOnInit() {
    this.getTopicsForModules();
    // this.getTopicsByModules();
    // console.log(this.topics);

    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;

      this.modId = this.route.snapshot.paramMap.get('id');

  });
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  userRole() {
    if (this.currentUser.role === 'ROLE_STUDENT') {
      console.log(this.currentUser);
      // console.log(this.topics);
      return true;
    } else {
      return false;
    }
  }

  getTopicsForModules() {
    this.api.getAllTopics().subscribe(data => {
      this.topics = data as any[];
      console.log(this.topics);
    });
    }

// getTopicsByModules() {
//   this.modId = this.route.snapshot.paramMap.get('id');
//     this.api.getTopicsById(this.modId).subscribe((data: any[]) => {
//       this.topics = data as any[];

//     });
//     }

    hideContent() {
// this.hide = true;
    }


    toggleContent(t) {
      this.modId = this.route.snapshot.paramMap.get('id');

    //   if (this.hide) {
    //     this.hide = false;
    // } else {
    //   this.hide = true;
    // }
this.topicHide = t;
this.topicHide.id = t.id;
this.topicHide.topicTitle = t.topicTitle;
this.topicHide.files = null;
this.topicHide.Quizzes = null;
this.topicHide.hidden = this.hide;
 this.apiT.createHidden(this.topicHide, this.modId).subscribe(res => {
this.allTopic.push(this.topicHide);
 });
  }

  toHide(hidden) {
    if (hidden === 'false') {
      return false;
    } else {
      return true;
    }
  }
}
