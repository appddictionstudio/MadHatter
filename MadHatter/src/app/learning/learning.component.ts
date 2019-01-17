import { Component, OnInit } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { FormGroup, FormBuilder, FormsModule, FormControl, Validators, FormArray } from '@angular/forms';
import { ModuleService } from '../services/module.service';
import { Module } from '../models/Module';
import { BootcampModule } from '../models/Bootcamp';
import { ActivatedRoute } from '@angular/router';
import { MockNgModuleResolver } from '@angular/compiler/testing';
import { detachEmbeddedView } from '@angular/core/src/view';
import {CommonModule} from '@angular/common';


@Component({
  selector: 'app-learning',
  templateUrl: './learning.component.html',
  styleUrls: ['./learning.component.scss']
})
export class LearningComponent implements OnInit {

  form: FormGroup;
  module: Module[] = [];
  route: ActivatedRoute;
  mod: any;
  isLoading = true;

  constructor(
    private fb: FormBuilder,
    private api: ModuleService,
  ) { }

  ngOnInit() {
    this.getModuleforLearning();
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

  // select(m: Module) {
  //   this.api.getModById(m.id).subscribe(
  //     data => {
  //       this.mod = data;
  //       console.log(m.id);
  // });
  // }
}
