import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit, Inject, OnChanges, Input } from '@angular/core';
import { BulletinBoardService } from 'src/app/services/bulletin-board.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

export interface Activities {
  id: number;
  eodTech: number;
  actType: number;
  timeStamp: number;
  post: number;
  }

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit, OnChanges {
closeResult: string;

  // activities: any[];
  activity: any;
  icon: any[];
  displayedColumns: string[] = ['icon', 'activity'];

  showComments = false;
  commentClick = false;
  likeClick = false;

  @Input() activities: any[];


  constructor(private api: BulletinBoardService, private modalService: NgbModal) { }


  // THIS IS THE POP UP MODAL FOR THE POST IDENTIFIED IN THE ACTIVITES CLICK //
  openModalCentered(content, activities) {
    console.log(activities);
    this.activity = activities;
    this.modalService.open(content, { centered: true });
  }

  ngOnInit() {
    // this.getAllActivity();
  }

  ngOnChanges() {
    // this.getAllActivity();
    // console.log('this is working');
  }

  commentSection() {
    this.showComments = !this.showComments;
    this.commentClick = !this.commentClick;

  }

  likeClickEvent() {
    this.likeClick = !this.likeClick;
  }


  // THIS WILL BE THE METHOD TO OPEN UP THE MODAL FROM THE ACTIVITIES COMPONENT //


}
