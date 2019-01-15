import { Component, OnInit } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { FormGroup, FormBuilder, FormsModule, FormControl, Validators, FormArray } from '@angular/forms';
import { ModuleService } from '../services/module.service';
import { Module } from '../models/Module';
import { BootcampModule } from '../models/Bootcamp';
import { Router } from '@angular/router';
import { MockNgModuleResolver } from '@angular/compiler/testing';


@Component({
  selector: 'app-learning',
  templateUrl: './learning.component.html',
  styleUrls: ['./learning.component.scss']
})
export class LearningComponent implements OnInit {

  form: FormGroup;
  module: Module[] = [];
  route: Router;
  mod: any;


  constructor(
    private fb: FormBuilder,
    private api: ModuleService
  ) { }

  ngOnInit() {
    this.getModuleforLearning();
  }
getModuleforLearning() {
this.api.getModule().subscribe(data => {
  this.module = data as any[];
});
}

  classesPresent() {
    return true;
  }

  select(m: Module) {
    this.api.getModById(m.id).subscribe(
      data => {
        this.mod = data;
        console.log(m.id);
  });
  }
}
