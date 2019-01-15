import { Component, OnInit } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-learning-details',
  templateUrl: './learning-details.component.html',
  styleUrls: ['./learning-details.component.scss']
})
export class LearningDetailsComponent implements OnInit {

  constructor(private api: ModuleService,
    private apiU: UserService) { }

  topics: Topic[] = [];
  hide = false;
  currentUser: any;
  ngOnInit() {
    this.getTopicsForModules();

    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
      console.log(this.currentUser);
  });
  }

  getTopicsForModules() {
    this.api.getAllTopics().subscribe(data => {
      this.topics = data as any[];
    });
    }

    hideContent() {
      this.hide = true;
    }
}
