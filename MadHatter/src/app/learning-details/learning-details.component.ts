import { Component, OnInit } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';

@Component({
  selector: 'app-learning-details',
  templateUrl: './learning-details.component.html',
  styleUrls: ['./learning-details.component.scss']
})
export class LearningDetailsComponent implements OnInit {

  constructor(private api: ModuleService) { }

  topics: Topic[] = [];

  ngOnInit() {
    this.getTopicsForModules();
  }

  getTopicsForModules() {
    this.api.getAllTopics().subscribe(data => {
      this.topics = data as any[];
    });
    }


}
