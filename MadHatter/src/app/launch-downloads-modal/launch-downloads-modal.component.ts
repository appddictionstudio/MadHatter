import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { LearningDetailsComponent } from '../learning-details/learning-details.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-launch-downloads-modal',
  templateUrl: './launch-downloads-modal.component.html',
  styleUrls: ['./launch-downloads-modal.component.scss']
})
export class LaunchDownloadsModalComponent implements OnInit {

  topic: any;
  title: string;
   tId: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  private route: ActivatedRoute,

  ) {

   }

  ngOnInit() {
    console.log(this.data);
    this.route.paramMap.subscribe(params => {
      this.tId = params.get('id');
    });
    console.log(this.tId);
  }

  populateTopicTitle(topic) {
    if (topic) {
      this.title = topic.topicTitle;
    }
  }

}
