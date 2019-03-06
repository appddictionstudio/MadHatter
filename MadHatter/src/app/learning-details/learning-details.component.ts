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
  learningDetailEdit = false;
  updateExerciseStatus = '';
  isLoading = true;
  documents: any[] = [];
  t: any[] = [];
  topicCenter: Topic = new Topic();
  topicslist: Topic[] = [];
  closeResult: string;
  attList: Attachments[] = [];
  modalTitle: any;
  topicsEdit: any;


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
    this.getAllTopics();
  }

  userRole() {
    if (this.currentUser.role === 'ROLE_STUDENT') {
      return true;
    } else {
      return false;
    }
  }

  editToggle() {
    if (!this.learningDetailEdit) {
      this.learningDetailEdit = true;
    } else {
      this.learningDetailEdit = false;
    }
  }

  getModuleforLearning() {
    this.api.getModById(this.modId).subscribe(res => {
      this.module = res as any[];
      this.modules = [this.module];
      // console.log(this.modules);
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

    toggleContent() {
      if (this.hide) {
        this.hide = false;
    } else {
      this.hide = true;
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
      this.topicsEdit = data as any[];
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


onFileChange(event, topic, m) {
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
    };
  }
}

  SaveTopic() {
    this.apiT.saveTopic(this.topicCenter).subscribe(data => {
      this.ngOnInit();
    });
  }

  updateTopic(topic, modId) {
    let Mod;
    this.api.getModuleById(modId).subscribe( results => {Mod = results; });
    // topic.mod = {id: modId};
    topic.mod = Mod;
    console.log(topic);
    // console.log(topicId);
    this.apiT.updateTopic(topic).subscribe(data => {
      this.snotifyService.success('Excercise Added', {
        timeout: 2000,
        showProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
    });
    this.ngOnInit();
  }

  updateModule(mod) {
    console.log(mod);
    this.api.updateMod(mod).subscribe(data => {
      this.snotifyService.success('File Uploaded', {
        timeout: 2000,
        closeOnClick: true,
        showProgressBar: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
    });
  }

  saveEdits() {
    // console.log(this.modules[0]);
    this.api.updateMod(this.modules[0]).subscribe(data => {});
    let i;
    // console.log(this.topics);
    for (i = 0; i < this.topics.length; i++) {
      // console.log(this.topics[i]);
      this.apiT.updateTopic(this.topics[i]).subscribe(data => {});
    }
    this.editToggle();
  }


  downloadAttatchemnts(attachmentId) {
    this.apiT.DownloadAtt(attachmentId).subscribe(response => {
      this.saveToFileSystem(response);
    });
  }

 removeAttachment(attachment) {
  this.apiT.deleteTopicAtt(attachment.id).subscribe(response => {
    attachment.id = null;
    this.snotifyService.success('File removed', {
      timeout: 2000,
      closeOnClick: true,
      showProgressBar: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerBottom
    });
  });
 }

  checkIfHidden(att) {
    if (!this.getUserRoleStudent()) {
      if (att.id) {
        return true;
      } else {
        return false;
      }
    } if (att.quiz !== 'N') {
      return true;
    } else {
      return false;
    }
  }

 ishidden(hidden) {
  if (hidden === 'true') {
    return true;
  } else {
    return false;
  }
 }

 private saveToFileSystem(response) {
   const contentDispositionHeader: string = response.headers.get(
     'Content-Disposition'
   );
   const parts: string[] = contentDispositionHeader.split(';');
   const filename = parts[1].split('=')[1];
   const blob = new Blob([response.body], { type: 'text/plain' });
   saveAs(blob, filename);
 }

 attachemntIsQuiz(desc) {
  if (desc === 'Quiz') {
    return true;
  } else {
    return false;
  }
 }

 getUserRoleInstructor() {
  if (this.currentUser.role === 'ROLE_ADMIN' || this.currentUser.role === 'ROLE_TEACHER_ASD' ||
  this.currentUser.role === 'ROLE_TEACHER_UI') {
    return true;
  } else {
    return false;
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
    // console.log(this.buttonAddResource);
    this.api.setResources(mod.id, this.resourceLinkInput).subscribe(res => {
      this.snotifyService.success('Resource Added', {
        timeout: 2000,
        closeOnClick: true,
        showProgressBar: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom
      });
      this.resourceLinkInput = '';
      this.ngOnInit();
    });
  }

  resourceLinkInputcheck() {
    if (this.resourceLinkInput.startsWith('http')) {
      this.buttonAddResource = true;
    } else {
      this.buttonAddResource = false;
    }
  }

  removeResourceLink(resource) {
    this.api.removeResources(resource.id).subscribe(res => {
      this.snotifyService.success('Resource removed', {
        position: SnotifyPosition.centerBottom,
        closeOnClick: true,
        timeout: 2000,
      });
      this.ngOnInit();
    });
  }

  updateExerciseStatusChange(t, a) {
    this.updateExerciseStatus = a.description;
    if (this.updateExerciseStatus === 'Quiz') {
      a.quiz = 'N';
    } if (this.updateExerciseStatus === 'Test') {
      a.quiz = 'N';
    } if (this.updateExerciseStatus === 'Exercise') {
      a.quiz = null;
    } if (this.updateExerciseStatus === 'Content') {
      a.quiz = null;
    }
    this.apiT.updateTopic(t).subscribe(data => {
      this.apiT.updateTopicAtt(a, t.id).subscribe(res => {
        this.snotifyService.success('Status Updated', {
          timeout: 2000,
          closeOnClick: true,
          showProgressBar: false,
          pauseOnHover: true,
          position: SnotifyPosition.centerBottom
        });
      });
    });
  }

  upArrowClick(i) {
    console.log(this.topics[i]);
    console.log(this.topics[i - 1]);
    this.topics[i].topicOrder = this.topics[i].topicOrder - 1;
    this.topics[i - 1].topicOrder = this.topics[i - 1].topicOrder + 1;
  }

  downArrowClick(i) {
    console.log(this.topics[i]);
    console.log(this.topics[i + 1]);
    this.topics[i].topicOrder = this.topics[i].topicOrder + 1;
    this.topics[i + 1].topicOrder = this.topics[i + 1].topicOrder - 1;
  }
}
