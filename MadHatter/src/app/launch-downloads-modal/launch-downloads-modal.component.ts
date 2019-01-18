import { Component, OnInit, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { LearningDetailsComponent } from '../learning-details/learning-details.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Topic } from '../models/Topic';
import { TopicsService } from '../services/topics.service';
import { Attachments } from '../models/Attachments';

@Component({
  selector: 'app-launch-downloads-modal',
  templateUrl: './launch-downloads-modal.component.html',
  styleUrls: ['./launch-downloads-modal.component.scss']
})
export class LaunchDownloadsModalComponent implements OnInit {
  private selectedTopicId: number;

@Input() topicobj: Topic;
// @Input() lesson: Lesson;

  topic: any[];
  title: string;
   tId: any;
   documents: any[] = [];
   topicslist: Topic[] = [];
   attachments: Attachments[] = [];
   att: any[] = [];
  constructor(
    // @Inject(MAT_DIALOG_DATA) public data,
  private route: ActivatedRoute,
  private router: Router,
  private api: TopicsService
  ) {
    // this.attachments = this.data;
   }

  ngOnInit() {
    // console.log(this.data);
    console.log(this.attachments);
    // this.route.paramMap.subscribe(params => {
    //   this.tId = params.get('id');
    // });
  // this.tId = +this.route.snapshot.paramMap.get('id');
      // console.log(this.tId);
    this.getAllAttachments();
    this.getAllTopics();
  }

  onFileChange(event) {
    console.log(event);
    const reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        const formData = new FormData();
        formData.append('file', file);
        this.api.uploadTopicAttachment(formData).subscribe(
          result => {
            this.documents.push(result);

          }
        );
      };
    }

  }
  getAllTopics() {
    this.api.getTopics().subscribe(data => {
      this.topicslist = data as any[];
    });
  }

  getAllAttachments() {
this.api.getTopicAtt().subscribe(data => {
  this.att = data as any[];
});
  }
// viewTopicD() {
//   this.router.navigate(['launch /', this.topic.id], {
//   });
}

