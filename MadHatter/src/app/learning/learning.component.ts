import { Component, OnInit } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { FormGroup, FormBuilder, FormsModule, FormControl, Validators, FormArray } from '@angular/forms';
import { ModuleService } from '../services/module.service';
import { Module } from '../models/Module';
import { UserService } from '../services/user.service';
import { BootcampModule } from '../models/Bootcamp';
import { ActivatedRoute } from '@angular/router';
import { MockNgModuleResolver } from '@angular/compiler/testing';
import { detachEmbeddedView } from '@angular/core/src/view';
import { Router } from '@angular/router';
import {CommonModule} from '@angular/common';
import { Topic } from '../models/Topic';


@Component({
  selector: 'app-learning',
  templateUrl: './learning.component.html',
  styleUrls: ['./learning.component.scss']
})
export class LearningComponent implements OnInit {

  form: FormGroup;
  module: Module[] = [];
  topic: Topic[] = [];
  route: ActivatedRoute;
  mod: any;
  hidden: any;
  moduleHide: any;
  isLoading = true;
  currentUser: any;
  UIorASD: number;

  constructor(
    private fb: FormBuilder,
    private apiU: UserService,
    private router: Router,
    private api: ModuleService,
  ) { }

  ngOnInit() {
    this.getModuleforLearning();
    this.getUserRole();
  }

  getModuleforLearning() {
    this.isLoading = true;
    this.api.getModule().subscribe(data => {
      this.module = data as any[];
      console.log(this.module);
      this.isLoading = false;
    });
  }

  classesPresent() {
    return true;
  }

  getUserRole() {
    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
    });
  }

  studentorteacherrole(role) {
  if (role === 'ASD') {
    if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    }if (this.currentUser.role === 'ROLE_STUDENT_ASD') {
      return true;
    }if (this.currentUser.role === 'ROLE_TEACHER_ASD') {
      return true;
    }
  }
  if (role === 'UI') {
    if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    }if (this.currentUser.role === 'ROLE_STUDENT_UI') {
      return true;
    }if (this.currentUser.role === 'ROLE_TEACHER_UI') {
      return true;
    }
  }
 }

 routeForInstructors(id) {
  if (this.currentUser.role === 'ROLE_TEACHER_UI') {
    this.router.navigateByUrl('/learning/learningdetail/' + id);
  } if (this.currentUser.role === 'ROLE_TEACHER_ASD') {
    this.router.navigateByUrl('/learning/learningdetail/' + id);
  } if (this.currentUser.role === 'ROLE_ADMIN') {
    this.router.navigateByUrl('/learning/learningdetail/' + id);
  }
 }
  // select(m: Module) {
  //   this.api.getModById(m.id).subscribe(
  //     data => {
  //       this.mod = data;
  //       console.log(m.id);
  // });
  // }
}
