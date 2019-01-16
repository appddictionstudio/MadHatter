import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
import { Module } from '../models/Module';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { TopicsService } from '../services/topics.service';
import { MatDialog } from '@angular/material';
import { LaunchDownloadsModalComponent } from '../launch-downloads-modal/launch-downloads-modal.component';

@Component({
  selector: 'app-learning-details',
  templateUrl: './learning-details.component.html',
  styleUrls: ['./learning-details.component.scss']
})
export class LearningDetailsComponent implements OnInit, OnChanges {

  constructor(
    public dialog: MatDialog,
    private api: ModuleService,
    private apiU: UserService,
    private route: ActivatedRoute,
    private apiT: TopicsService
    ) { }

  topics: Topic[] = [];
  topicHide: Topic = new Topic();
  allTopic: Topic[] = [];
  // hide: string;
  modules: Module[] = [];
  hide = false;
  currentUser: any;
  modId: any;
  module: any;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.modId = params.get('id');
    });
    this.getModuleforLearning();
    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
    });
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  userRole() {
    if (this.currentUser.role === 'ROLE_STUDENT') {
      return true;
    } else {
      return false;
    }
  }

  getModuleforLearning() {
    this.api.getModById(this.modId).subscribe(res => {
      this.module = res as any[];
      this.modules = [this.module];
      this.topics = JSON.parse(JSON.stringify(this.module.topicId));
    });
    }

    hideContent() {
      this.hide = false;
    }
    launch(t,index) {
       const dialogRef = this.dialog.open(LaunchDownloadsModalComponent , {
        height: '420px',
        width: '600px',
        data: {
          selectedTopic: t,
        }
      });
       dialogRef.afterClosed().subscribe(result => {
        //  console.log('Dialog result: ${result}');
       });
    }
    toggleContent() {
      if (this.hide) {
        this.hide = false;
    } else {
      this.hide = true;
    }
  }

//     toggleContent(t) {
//       this.modId = this.route.snapshot.paramMap.get('id');

//     //   if (this.hide) {
//     //     this.hide = false;
//     // } else {
//     //   this.hide = true;
//     // }
// this.topicHide = t;
// this.topicHide.id = t.id;
// this.topicHide.topicTitle = t.topicTitle;
// this.topicHide.files = null;
// this.topicHide.Quizzes = null;
// this.topicHide.hidden = this.hide;
//  this.apiT.createHidden(this.topicHide, this.modId).subscribe(res => {
// this.allTopic.push(this.topicHide);
//  });
//   }


}
