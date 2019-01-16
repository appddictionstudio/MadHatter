import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
import { Module } from '../models/Module';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-learning-details',
  templateUrl: './learning-details.component.html',
  styleUrls: ['./learning-details.component.scss']
})
export class LearningDetailsComponent implements OnInit, OnChanges {

  constructor(private api: ModuleService,
    private apiU: UserService,
    private route: ActivatedRoute,
    ) { }

  topics: Topic[] = [];
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
    this.api.getModuleById(this.modId).subscribe(res => {
      this.module = res as any[];
      this.modules = [this.module];
      this.topics = JSON.parse(JSON.stringify(this.module.topicId));
      console.log(this.modules);
      console.log(this.topics);
    });
    }

    hideContent() {
      this.hide = true;
    }

    toggleContent() {
      if (this.hide) {
        this.hide = false;
    } else {
      this.hide = true;
    }
  }

  toHide(hidden) {
    if (hidden === 'false') {
      return false;
    } else {
      return true;
    }
  }
}
