import { Component, OnInit } from '@angular/core';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import { FormGroup, FormBuilder, FormsModule, FormControl, Validators, FormArray } from '@angular/forms';
import { ModuleService } from '../services/module.service';
import { Module } from '../models/Module';


@Component({
  selector: 'app-learning',
  templateUrl: './learning.component.html',
  styleUrls: ['./learning.component.scss']
})
export class LearningComponent implements OnInit {

  form: FormGroup;
  module: Module[] = [];
  api: ModuleService;

  constructor(
    private fb: FormBuilder,
  ) { }

  ngOnInit() {
  }
  postModule(desc: string, title: string, topic: number) {

    const obj: Module = {
      id: null,
      description: desc,
      end_date: null,
      start_date: null,
      title: title,
      topic: topic,
    };
    console.log(obj);

      this.api.setModuleInformation(obj).subscribe(result => {
        console.log('done');
      });
  }

}
