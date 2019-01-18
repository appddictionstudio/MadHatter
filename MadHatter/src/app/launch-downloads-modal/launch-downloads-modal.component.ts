import { Component, OnInit, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { LearningDetailsComponent } from '../learning-details/learning-details.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Topic } from '../models/Topic';
import { TopicsService } from '../services/topics.service';
import { Attachments } from '../models/Attachments';
import { NgbModalConfig, NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-launch-downloads-modal',
  templateUrl: './launch-downloads-modal.component.html',
  styleUrls: ['./launch-downloads-modal.component.scss'],
  providers: [NgbModalConfig, NgbModal]

})
export class LaunchDownloadsModalComponent implements OnInit {
  private selectedTopicId: number;

@Input() topicobj: Topic;

  topic: any[];
  title: string;
   tId: any;
   documents: any[] = [];
   topicslist: Topic[] = [];
   attachments: Attachments[] = [];
   att: any[] = [];
   closeResult: string;

  constructor(
    // @Inject(MAT_DIALOG_DATA) public data,
  private route: ActivatedRoute,
  private router: Router,
  private api: TopicsService,
  private modalService: NgbModal,
    config: NgbModalConfig
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
this.api.getTopicAtt().subscribe(data =>{
  this.att = data as any[];
});
  }
// viewTopicD() {
//   this.router.navigate(['launch /', this.topic.id], {
//   });


open(content) {
  this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
  }, (reason) => {
    this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  });
}
private getDismissReason(reason: any): string {
  if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
  } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
  } else {
    return  `with: ${reason}`;
  }
}
}

