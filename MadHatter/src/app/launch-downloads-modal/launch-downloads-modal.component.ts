import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-launch-downloads-modal',
  templateUrl: './launch-downloads-modal.component.html',
  styleUrls: ['./launch-downloads-modal.component.scss']
})
export class LaunchDownloadsModalComponent implements OnInit {

  selectedtopic: any;
  topic: any[];
  title: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  ) {
    this.topic = data.selectedTopic;
   }

  ngOnInit() {
    console.log(this.topic);
  }

  populateTopicTitle(topic) {
    if (topic) {
      this.title = topic.topicTitle;
    }
  }

}
