import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ModuleService } from '../services/module.service';
import { Topic } from '../models/Topic';
import { Module } from '../models/Module';
import { UserService } from '../services/user.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import { TopicsService } from '../services/topics.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit, OnChanges {

  constructor(
    private api: ModuleService,
    private apiU: UserService,
    private apiT: TopicsService,
  ) { }

  currentUser: any;
  userRole: any;
  modId: any;
  hide = false;
  module: any;
  mod: any;
  topics: Topic[] = [];
  topicHide: Topic = new Topic();
  allTopic: Topic[] = [];
  modules: Module[] = [];
  mods: Module[] = [];
  isLoading = true;
  teacherRole: number;

  ngOnInit() {
    this.getUserRole();
    this.getModuleforLearning();
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  getModuleforLearning() {
    this.api.getModule().subscribe(res => {
      this.modules = res as any[];
      console.log(this.modules);
      this.isLoading = false;
    });
  }

  getUserRole() {
    this.apiU.getUser().subscribe(data => {
      this.currentUser = data;
    });
  }

  getUserRoleStudent() {
    if (this.currentUser.role === 'ROLE_STUDENT') {
      return true;
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

  getUserRoleAdmin() {
    if (this.currentUser.role === 'ROLE_ADMIN') {
      return true;
    }
  }

  toggleContent(topic, modId) {
    if (topic.hidden === 'false') {
      topic.hidden = 'true';
      topic.mod = {id: modId};
      this.apiT.updateTopic(topic).subscribe(data => {

      });
    } else {
      topic.hidden = 'false';
      topic.mod = {id: modId};
      this.apiT.updateTopic(topic).subscribe(data => {

      });
    }
  }

  ishidden(hidden) {
    if (hidden === 'true') {
      return true;
    } else {
      return false;
    }
  }

}
