import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
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
  hide = false;
  currentUser: any;
  modId: any;

  ngOnInit() {
    this.getTopicsForModules();
    // this.getTopicsByModules();
    console.log(this.topics);

    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
      console.log(this.currentUser);

      this.modId = this.route.snapshot.paramMap.get('id');
      console.log(this.modId);

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

// getTopicsByModules() {
//   this.modId = this.route.snapshot.paramMap.get('id');
//     this.api.getTopicsById(this.modId).subscribe((data: any[]) => {
//       this.topics = data as any[];

//     });
//     }

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

}
