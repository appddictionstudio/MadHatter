import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
import { Module } from '../models/Module';
import { UserService } from '../services/user.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import { TopicsService } from '../services/topics.service';
import { StudentService } from '../services/student.service';
import { Attachments } from '../models/Attachments';
import {NgbModalConfig, NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { saveAs } from 'file-saver';
import { Router, RouterModule } from '@angular/router';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TopicAtt } from '../models/TopicAtt';
import { SubmittedAtt } from '../models/SubmittedAtt';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
  providers: [NgbModalConfig, NgbModal]
})
export class AdminComponent implements OnInit, OnChanges {
  topicQuiz: any;
  modulesASD: any[];
  modulesUI: any[];

  constructor(
    private api: ModuleService,
    private apiU: UserService,
    private apiT: TopicsService,
    private router: Router,
    private snotifyService: SnotifyService,
    private modalService: NgbModal,
    private apiS: StudentService,
  ) { }

  currentUser: any;
  userRole: any;
  modId: any;
  hide = false;
  module: any;
  exercise = 'Exercise';
  quiz = 'Quiz';
  mod: any;
  updateModuleProgress = [null];
  attId: any;
  topics: any;
  topicHide: Topic = new Topic();
  allTopic: Topic[] = [];
  modules: Module[] = [];
  mods: Module[] = [];
  result: SubmittedAtt;
  fileUploading: boolean;
  allStudentAttempts: any;
  studentHasAttempt: boolean;
  gettingStudentAttemps: boolean;
  hasModalBeenChecked = [null];
  isLoading = true;
  teacherRole: number;
  studentRole: number;
  documents: any[] = [];
  closeResult: string;
  attList: Attachments[] = [];
  attList2: Attachments[] = [];
  topicAtt: TopicAtt[] = [];
  subAtt: SubmittedAtt = new SubmittedAtt();
  UIorASD: number;
  numerator: number;
  modGrade = [0];
  modCount = [0];
  modGradeTotal = [0];
  studentAttempts = [null];
  evaluatedAttempts = [null];
  hasGottenStartingProgress = [null];
  startProgress = [null];
  userAttCheck = [null];
  showSubmitButton = [null];

  ngOnInit() {
    this.getUserRole();
    this.getModuleforLearning();
    this.getAllTopics();
    this.getAttachments();
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  getModuleforLearning() {
    this.api.getModuleByBootcamp('SD').subscribe(res => {
      this.modulesASD = res as any[];
    console.log(this.modulesASD);
    });
    this.api.getModuleByBootcamp('UI').subscribe(res => {
      this.modulesUI = res as any[];
    console.log(this.modulesUI);
    });
  }

  getUserRole() {
    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
      if (this.currentUser === 'ROLE_STUDENT_ASD') {
        this.UIorASD = 1;
      }
      if (this.currentUser === 'ROLE_TEACHER_ASD') {
        this.UIorASD = 1;
      }
      if (this.currentUser === 'ROLE_TEACHER_UI') {
        this.UIorASD = 2;
      }
      if (this.currentUser === 'ROLE_TEACHER_UI') {
        this.UIorASD = 2;
      }
    });
  }

  getStudentAttempts(id) {
    this.gettingStudentAttemps = true;
    this.attId = id;
    this.apiS.getStudentAttempts(id).subscribe(data => {
      this.studentAttempts = data as any[];
      this.gettingStudentAttemps = false;
    });
  }

  submitGrade(s) {
    this.apiS.gradeStudent(s, this.attId).subscribe(data => {
      this.snotifyService.success('Student grade updated', {
        timeout: 3000,
        closeOnClick: true,
        showProgressBar: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom,
      });
    });
  }

  getStudentAttemptsByLesson() {
    this.apiS.getStudentAttemptsByLesson(this.currentUser.id).subscribe(data => {
      this.allStudentAttempts = data as any[];
      this.isLoading = false;
    });
  }

  getUserRoleStudent() {
    if (this.currentUser.role === 'ROLE_STUDENT_ASD') {
      return true;
    } if (this.currentUser.role === 'ROLE_STUDENT_UI') {
      return true;
    }
  }

  teachingRole(role) {
    if (role === 'ASD') {
      if (this.currentUser.role === 'ROLE_TEACHER_UI') {
        return false;
      } if (this.currentUser.role === 'ROLE_TEACHER_ASD') {
        this.teacherRole = 2;
        return true;
      } if (this.currentUser.role === 'ROLE_ADMIN') {
        return true;
      }
    }
    if (role === 'UI') {
      if (this.currentUser.role === 'ROLE_TEACHER_UI') {
        return true;
      } if (this.currentUser.role === 'ROLE_TEACHER_ASD') {
        this.teacherRole = 2;
        return false;
      } if (this.currentUser.role === 'ROLE_ADMIN') {
        return true;
      }
    }
  }

  studentsRole(role) {
    if (role === 'ASD') {
      if (this.currentUser.role === 'ROLE_STUDENT_UI') {
        return false;
      } if (this.currentUser.role === 'ROLE_STUDENT_ASD') {
        return true;
      }
    }
    if (role === 'UI') {
      if (this.currentUser.role === 'ROLE_STUDENT_UI') {
        return true;
      } if (this.currentUser.role === 'ROLE_STUDENT_ASD') {
        return false;
      }
    }
  }

  getUserRoleInstructor() {
    if (this.currentUser.role === 'ROLE_TEACHER_UI') {
      this.teacherRole = 1;
      return true;
    } if (this.currentUser.role === 'ROLE_TEACHER_ASD') {
      this.teacherRole = 2;
      return true;
    } if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    }
  }

  studentHasAttemptOnRecord(att) {
    if (att.description === 'Exercise' || att.description === 'Quiz' || att.description === 'Test') {
      if (!this.userAttCheck[att.id]) {
        this.userAttCheck[att.id] = true;
        this.apiS.checkStudentAttemptsByLesson(this.currentUser.id, att.id).subscribe(data => {
          if (data) {
            this.showSubmitButton[att.id] = false;
            return false;
          } else {
            this.showSubmitButton[att.id] = true;
            return true;
          }
        });
      } else {
        return this.showSubmitButton[att.id];
      }
    }
  }

  getUserRoleAdmin() {
    if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    }
  }

  toggleContent(topic, modId) {
    if (topic.hidden) {
      topic.hidden = false;
      topic.mod = {id: modId};
      this.apiT.updateTopic(topic).subscribe(data => {
      });
      this.hide = false;
    } else {
      topic.hidden = true;
      topic.mod = {id: modId};
      this.apiT.updateTopic(topic).subscribe(data => {
      });
      this.hide = true;
    }
  }

  toggleQuizContent(att, Tid) {
    if (att.quiz === 'Y') {
      att.quiz = 'N';
      this.apiT.updateTopicAtt(att, Tid).subscribe(data => {
      });
    } else {
      att.quiz = 'Y';
      this.apiT.updateTopicAtt(att, Tid).subscribe(data => {
      });
    }
  }

getAttachments() {
  this.apiT.getTopicAtt().subscribe(data => {
    this.attList = data as any[];
});
}
getTopicAttById(topicAttId) {
  this.apiT.getTopicAttById(topicAttId).subscribe(data => {
    this.attList2 = data as any[];
});
}
  getAllTopics() {
    this.api.getTopicsByAll().subscribe(res => {
      this.topics = res as any[];
      this.getStudentAttemptsByLesson();
    });
  }

  onFileChange(event, topicAtt) {
    if (this.studentAttempts[topicAtt.id]) {
      this.snotifyService.error('Only one attempt per exercise', {
        timeout: 3000,
        closeOnClick: true,
        showProgressBar: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom,
      });
    } else {
      this.fileUploading = topicAtt.id;
      const reader = new FileReader();
      if (event.target.files && event.target.files.length > 0) {
        const file = event.target.files[0];
        reader.readAsDataURL(file);
        reader.onload = () => {
          const formData = new FormData();
          formData.append('file', file);
          this.apiS.uploadStudentAttachment(formData).subscribe(
            results => {
              // this.fileUploading = null;
              this.result = results as SubmittedAtt;
              this.documents.push(this.result);
              topicAtt.subAtt.push(this.result);
            }
          );
        };
      }
    }
  }
  updateSubmittedAtt(topicAtt, index) {
    this.fileUploading = topicAtt;
    this.result.topicAtt = topicAtt;
    this.result.student = this.currentUser;
    this.apiS.updateTopicAtt(this.result, topicAtt).subscribe(data => {
      this.fileUploading = null;
      this.snotifyService.success(this.result.fileNm + ' was uploaded for grading', {
        timeout: 3000,
        closeOnClick: true,
        showProgressBar: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom,
      });
      this.topicAtt[index] = data;
      this.subAtt.topicAtt = topicAtt;
    });
  }

  ishidden(hidden) {
    if (hidden) {
      this.hide = false;
      return true;
    } else {
      this.hide = true;
      return true;
    }
  }

  downloadAttatchemnts(attachmentId) {
    if (attachmentId.description === 'Quiz') {
      return true;
    } else {
      this.apiT.DownloadAtt(attachmentId.id).subscribe(response => {
        this.saveToFileSystem(response);
      });
    }
 }
 downloadModuleAttachment(attachmentId) {
   this.api.DownloadMod(attachmentId).subscribe(response => {
   this.saveToFileSystem(response);
 });
 }

 downloadStudentAttatchemnts(attachmentId) {
  this.apiS.DownloadStudentAtt(attachmentId).subscribe(response => {
     this.saveToFileSystem(response);
   });
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

 open(content, id) {
   this.getStudentAttempts(id);
  this.modalService.open(content, {ariaLabelledBy: 'ngbd-modal-confirm'}).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
  }, (reason) => {
    this.closeResult = `Dismissed`;
  });
}
open2(content) {
 this.modalService.open(content, {ariaLabelledBy: 'ngbd-modal-confirm'}).result.then((result) => {
   this.closeResult = `Closed with: ${result}`;
 }, (reason) => {
   this.closeResult = `Dismissed`;
 });
}
  deleteTopicAtt(id, index) {
    this.apiT.deleteTopicAtt(id).subscribe(() => {
    this.topicAtt.splice(index);
    });
  }

  tryingsomething() {
    this.topics.attachments.attempts = this.studentAttempts;
  }

  calcGrade(n, d, a, m) {
    if (n > 0) {
      const grade = n / d * 100;
      this.studentAttempts[a] = true;
      return grade;
    } else {
      this.studentAttempts[a] = true;
      return 'Not Graded';
    }
  }

  displayModuleProgress(id, progress) {
    if (!this.hasModalBeenChecked[id]) {
      if (progress === 1) {
        this.hasModalBeenChecked[id] = true;
        this.startProgress[id] = 'Not Started';
        this.updateModuleProgress[id] = 'Not Started';
      } if (progress === 2) {
        this.hasModalBeenChecked[id] = true;
        this.startProgress[id] = 'In Progress';
        this.updateModuleProgress[id] = 'In Progress';
      } if (progress === 3) {
        this.hasModalBeenChecked[id] = true;
        this.startProgress[id] = 'Completed';
        this.updateModuleProgress[id] = 'Completed';
      }
    } else {
      return 'Current status is '  + this.startProgress[id];
    }
  }

  updateModuleProgressPlaceholder(id, progress) {
    if (progress === 1) {
      this.updateModuleProgress[id] = 'Not Started';
    } if (progress === 2) {
      this.updateModuleProgress[id] = 'In Progress';
    } if (progress === 3) {
      this.updateModuleProgress[id] = 'Completed';
    }
  }

  updateModuleProgressChange(m) {
    if (this.updateModuleProgress[m.id] === 'Not Started') {
      m.progress = 1;
    } if (this.updateModuleProgress[m.id] === 'In Progress') {
      m.progress = 2;
    } if (this.updateModuleProgress[m.id] === 'Completed') {
      m.progress = 3;
    }
    const mod = m;
    this.api.updateMod(mod).subscribe(res => {
      this.snotifyService.success('Module Status updated', {
        timeout: 3000,
        closeOnClick: true,
        showProgressBar: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom,
      });
    });
  }

  notStartedModule(m) {
    if (m === 1) {
      return true;
    }
    if (m === 2) {
      return false;
    }
    if (m === 3) {
      return false;
    }
  }

  studentHasNoAttempt(user, att) {
    if (!this.evaluatedAttempts[att]) {
      if (user === att) {
        this.studentAttempts[att] = true;
        this.evaluatedAttempts[att] = true;
      } else {
        this.studentAttempts[att] = false;
        this.evaluatedAttempts[att] = true;
      }
    } else {
    }
    return true;
  }

  routeQuiz(path) {
    this.router.navigateByUrl('/' + path + '/story.html');
  }

  getModGrade(m) {
    if (this.modGrade[m] === null) {
      return 'blank';
    } if (this.modGrade[m] > 0) {
      this.numerator = this.modGrade[m];
      return this.modGrade[m];
    } else {
      return 'blank else';
    }
  }

  deleteTeacherContent(a, i, location, attId) {
    this.api.removeModAttachment(attId).subscribe(data => {
      this.snotifyService.success('File removed', {
        timeout: 2000,
        closeOnClick: true,
        showProgressBar: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerBottom,
      });
      if (location === 'ASD') {
        this.modulesASD[i].modAttachments.splice(a, 1);
      } if (location === 'UI') {
        this.modulesUI[i].modAttachments.splice(a, 1);
      }
    });
  }

  removeTeacherContent(a, i, location, attId) {
    this.snotifyService.warning('Are you sure you want to remove this file?', {
      timeout: 100000,
      closeOnClick: true,
      buttons: [
        {text: 'Yes', action: () => this.deleteTeacherContent(a, i, location, attId), bold: true },
        {text: 'No', action: null },
      ],
      showProgressBar: false,
      pauseOnHover: false,
      position: SnotifyPosition.centerBottom,
    });
  }
}
