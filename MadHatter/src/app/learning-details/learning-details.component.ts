import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
import { Module } from '../models/Module';
import { Resources } from '../models/Resources';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TopicsService } from '../services/topics.service';
import { MatDialog } from '@angular/material';
import { LaunchDownloadsModalComponent } from '../launch-downloads-modal/launch-downloads-modal.component';
import {NgbModalConfig, NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { saveAs } from 'file-saver';
import { Attachments } from '../models/Attachments';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';

@Component({
  selector: 'app-learning-details',
  templateUrl: './learning-details.component.html',
  styleUrls: ['./learning-details.component.scss'],
  providers: [NgbModalConfig, NgbModal]
})
export class LearningDetailsComponent implements OnInit, OnChanges {

  constructor(
    public dialog: MatDialog,
    private api: ModuleService,
    private apiU: UserService,
    private route: ActivatedRoute,
    private apiT: TopicsService,
    private router: Router,
    private modalService: NgbModal,
    config: NgbModalConfig,
    private snotifyService: SnotifyService,
    ) {
      config.backdrop = 'static';
    config.keyboard = false;
    }
  topic: any[] = [];
  topics: Topic[] = [];
  topicHide: Topic = new Topic();
  allTopic: Topic[] = [];
  // hide: string;
  modules: Module[] = [];
  resources: any;
  hide = false;
  currentUser: any;
  buttonAddResource = false;
  currentmod: any;
  resourceLinkInput = '';
  fileUploading: any;
  modfileUploading = false;
  modId: any;
  module: any;
  updateExerciseStatus = '';
  isLoading = true;
  documents: any[] = [];
  t: any[] = [];
  topicCenter: Topic = new Topic();
  topicslist: Topic[] = [];
  closeResult: string;
  attList: Attachments[] = [];


  ngOnInit() {
    this.fileUploading = null;
    this.route.paramMap.subscribe(params => {
      this.modId = params.get('id');
    });
    // console.log(this.modId);
    this.getModuleforLearning();
    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
    });
    // this.getAllTopics();
    // this.getAtt();
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
      this.resources = JSON.parse(JSON.stringify(this.module.resources));
      // console.log(this.resources);
      this.getTopicsByModId(this.module.id);
      // this.topics = JSON.parse(JSON.stringify(this.module.topic));
    });
    }

    hideContent() {
      this.hide = true;
    }

    launch(t, index) {

       const dialogRef = this.dialog.open(LaunchDownloadsModalComponent , {
        height: '420px',
        width: '600px',
        data: {
          selectedTopicid: t,
        }
      });
       dialogRef.afterClosed().subscribe(result => {
       });
      //  console.log(t);
    }

    open(content, t, m) {
      this.modalService.open(content, {ariaLabelledBy: 'ngbd-modal-confirm'}).result.then((result) => {
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

    // open(content) {
    //   const modalRef = this.modalService.open(LaunchDownloadsModalComponent);
    // }

    toggleContent() {
      if (this.hide) {
        this.hide = false;
    } else {
      this.hide = true;
    }
  }
  getLearningDetailAttType(desc) {
    if (desc === 'exercise') {
      return 'Exercise';
    } else {
      return 'Downloadable Content';
    }
  }
  ifResources() {
    if (this.resources.length > 0 ) {
      return true;
    } else {
      return false;
    }
  }

  getAllTopics() {
    this.apiT.getTopics().subscribe(data => {
      this.topicslist = data as any[];
    });
  }

  getTopicsByModId(modId) {
    this.apiT.getTopicsByModId(modId).subscribe(data => {
      this.topics =  data as any[];
      console.log(data);
      // console.log(this.topics);
      this.isLoading = false;
    });
  }

  hiddenQuiz(quiz) {
    if (quiz === 'Y') {
      return false;
    } if (quiz === 'N') {
      return true;
    }
    return false;
  }

  getAtt() {
    this.apiT.getAttachments().subscribe(data => {
      this.attList = data as any[];
    });
  }


onFileChange(event, topic, m) {
  console.log(topic + ' ' + m);
  this.fileUploading = topic.id;
  const reader = new FileReader();
  if (event.target.files && event.target.files.length > 0) {
    const file = event.target.files[0];
    reader.readAsDataURL(file);
    reader.onload = () => {
      const formData = new FormData();
      formData.append('file', file);
      this.apiT.uploadTopicAttachment(formData).subscribe(
        result => {
          this.fileUploading = null;
          this.documents.push(result);
          topic.attachments.push(result);
          this.updateTopic(topic, m);
        }
      );
    };
  }
}
onFileChange2(event, mod) {
  this.modfileUploading = true;
  const reader = new FileReader();
  if (event.target.files && event.target.files.length > 0) {
    const file = event.target.files[0];
    reader.readAsDataURL(file);
    reader.onload = () => {
      const formData = new FormData();
      formData.append('file', file);
      this.api.uploadModAttachment(formData).subscribe(
        result => {
          this.documents.push(result);
         mod.modAttachments.push(result);
         this.modfileUploading = false;
         this.updateModule(mod);
        }
      );
      // console.log(mod);
    };
  }
}

  SaveTopic() {
  this.apiT.saveTopic(this.topicCenter).subscribe(data => {
    this.ngOnInit();
  });
  }

  updateTopic(topicId, modId) {
    // this.topicCenter.attachments = this.documents;
    // tslint:disable-next-line:radix
    topicId.mod = {id: modId};
    console.log(topicId);
    this.apiT.updateTopic(topicId).subscribe(data => {
      this.snotifyService.success('Excercise Added', {
        timeout: 2000,
        showProgressBar: true,
        closeOnClick: true,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
    });
    // console.log(topicId);
    this.ngOnInit();
  }

  updateModule(mod) {
    this.api.updateMod(mod).subscribe(data => {
      this.snotifyService.success('File Uploaded', {
        timeout: 2000,
        showProgressBar: true,
        closeOnClick: true,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
  });
  // console.log(mod);
}


downloadAttatchemnts(attachmentId) {
  this.apiT.DownloadAtt(attachmentId).subscribe(response => {
    // console.log(response);
    this.saveToFileSystem(response);
  });
 }

 removeAttachment(attachmentId) {
  this.apiT.deleteTopicAtt(attachmentId).subscribe(response => {
    this.snotifyService.success('File removed', {
      timeout: 2000,
      showProgressBar: true,
      closeOnClick: true,
      pauseOnHover: true,
      position: SnotifyPosition.centerBottom
    });
    this.getTopicsByModId(this.module.id);
  });
 }

 ishidden(hidden) {
  if (hidden === 'true') {
    return true;
  } else {
    return false;
  }
 }

 private saveToFileSystem(response) {
  // console.log('saving file');
   const contentDispositionHeader: string = response.headers.get(
     'Content-Disposition'
   );
   const parts: string[] = contentDispositionHeader.split(';');
   const filename = parts[1].split('=')[1];
   const blob = new Blob([response.body], { type: 'text/plain' });
   saveAs(blob, filename);
 }

 getUserRoleInstructor() {
    if (this.currentUser) {
      if (this.currentUser.role === 'ROLE_TEACHER_ASD') {
        return true;
      } if (this.currentUser.role === 'ROLE_TEACHER_UI') {
        return true;
    } if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    } else {
      return false;
    }
  }
  }

  getUserRoleStudent() {
    if (this.currentUser) {
      if (this.currentUser.role === 'ROLE_STUDENT_ASD') {
        return true;
      } if (this.currentUser.role === 'ROLE_STUDENT_UI') {
        return true;
    } else {
      return false;
    }
  }
  }

  addResources(mod) {
    // this.resources.links = this.resourceLinkInput;
    console.log(this.buttonAddResource);
    this.api.setResources(mod.id, this.resourceLinkInput).subscribe(res => {
      this.snotifyService.success('Resource Added', {
        timeout: 2000,
        showProgressBar: true,
        closeOnClick: true,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
      this.resourceLinkInput = '';
    });
  }

  resourceLinkInputcheck() {
    if (this.resourceLinkInput.startsWith('http')) {
      this.buttonAddResource = true;
    } else {
      this.buttonAddResource = false;
    }
  }

  updateExerciseStatusChange(t) {
    t.attachments.description = this.updateExerciseStatus;
    if (this.updateExerciseStatus === 'Quiz') {
      t.attachments.quiz = 'Y';
    } if (this.updateExerciseStatus === 'Test') {
      t.attachments.quiz = 'Y';
    } if (this.updateExerciseStatus === 'Exercise') {
      t.attachments.quiz = null;
    } if (this.updateExerciseStatus === 'Content') {
      t.attachments.quiz = null;
    }
    this.apiT.updateTopic(t).subscribe(data => {
      this.snotifyService.success('Status Updated', {
        timeout: 2000,
        showProgressBar: true,
        closeOnClick: true,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
    });
  }
}
