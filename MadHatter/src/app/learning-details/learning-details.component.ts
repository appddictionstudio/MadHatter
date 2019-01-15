import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-learning-details',
  templateUrl: './learning-details.component.html',
  styleUrls: ['./learning-details.component.scss']
})
export class LearningDetailsComponent implements OnInit, OnChanges {

  constructor(private api: ModuleService,
    private apiU: UserService) { }

  topics: Topic[] = [];
  hide = false;
  currentUser: any;

  ngOnInit() {
    this.getTopicsForModules();

    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
    });
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  userRole() {
    if (this.currentUser.role === 'ROLE_STUDENT') {
      console.log(true);
      console.log(this.currentUser.hidden);
      return true;
    } else {
      console.log(false);
      return false;
    }
  }

  getTopicsForModules() {
    this.api.getAllTopics().subscribe(data => {
      this.topics = data as any[];
    });
    }

    toggleContent() {
      if (this.hide) {
        this.hide = false;
    } else {
      this.hide = true;
    }
  }
}
