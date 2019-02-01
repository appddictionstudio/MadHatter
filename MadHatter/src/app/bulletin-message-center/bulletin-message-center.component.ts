import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BulletinMessageCenter } from '../models/bulletinBoard';
import { BulletinBoardService } from '../services/bulletin-board.service';
import { SearchService } from '../bulletin-board/search/search.service';
import { RefCodeService } from '../services/ref-code.service';
import { UserService } from '../services/user.service';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { ActivityComponent } from '../bulletin-board/newsfeed/activity/activity.component';
import { FormBuilder } from '@angular/forms';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { Tag } from '../models/Tag';
import { MatChipInputEvent, PageEvent } from '@angular/material';

@Component({
  selector: 'app-bulletin-message-center',
  templateUrl: './bulletin-message-center.component.html',
  styleUrls: ['./bulletin-message-center.component.scss']
})
export class BulletinMessageCenterComponent implements OnInit {
  @Input() posts: BulletinMessageCenter[];
  @Output() updatePosts: EventEmitter<any> = new EventEmitter();


  hotTopicPost = false;
  isFavorite = false;
  showPostSection = true;
  showMessagesSection = false;
  fileUploading = false;
  bulletinBoardPost: BulletinMessageCenter = new BulletinMessageCenter();

  selectedPeopleAndGroupArray: any[] = [];
  urls = new Array<string>();
  documents: any[] = [];
  tagsOpen = false;
  tagName: string;
  visible = true;
  selectable = true;
  removable = true;
  currentUser: any;
  currentDept: any;

  selectedValue;
  selectedGroupArray: any[] = [];
  selectedPeopleArray: any[] = [];
  groupAndPeoplePosts: any[] = [[]];

  showgroupAndPeoplePosts = false;

  showGroups = false;
  showPeople = false;
  tags: Tag[] = [
    { name: 'Administrative' },
    { name: 'Equipment' },
    { name: 'Incident Reports' },
    { name: 'Operations' },
    { name: 'Supply' },
    { name: 'Technical Data' },
    { name: 'Training' },
    { name: 'Safety' },
  ];

  groupsArray = [
    'Unit',
    'Command',
    'AirForce',
    'Marines',
    'Navy'
  ];



  constructor(
    private api: BulletinBoardService,
    private searchApi: SearchService,
    private refApi: RefCodeService,
    private userService: UserService,
    private snotifyService: SnotifyService,
    private activity: ActivityComponent,
    private fb: FormBuilder,
    config: NgbCarouselConfig
  ) { }

  ngOnInit() {
    this.createBulletinPost();

    this.bulletinBoardPost.topic = '';
    this.bulletinBoardPost.text = '';
    this.bulletinBoardPost.attachments.length = 0;


    this.userService.getUser().subscribe(data => {
      this.currentUser = data;
    });

  }

  postSection() {
    this.showPostSection = true;
    this.showMessagesSection = false;
  }


  messagesSection() {
    this.showPostSection = false;
    this.showMessagesSection = true;
  }
  setHotTopic() {
    if (this.hotTopicPost) {
      this.hotTopicPost = false;
    } else {
      this.hotTopicPost = true;
    }
  }
  openTags() {
    this.tagsOpen = !this.tagsOpen;
  }
  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our tags
    if ((value || '').trim()) {
      this.tags.push({ name: value.trim() });
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  onFileChange(event) {
    // console.log(event);
    // this.fileUploading = true;
    // const files = event.target.files;
    // if (files && files.lenght > 0) {
    //   for (const file of files) {
    //     const imgReader = new FileReader();
    //     // tslint:disable-next-line:no-shadowed-variable
    //     imgReader.onload = (event: any) => {
    //       this.urls.push(event.target.result);
    //     };
    //     imgReader.readAsDataURL(file);
    //     this.fileUploading = false;
    //   }
    // }
    this.fileUploading = true;
    const reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        const formData = new FormData();
        formData.append('file', file);
        this.api.uploadBulletinBoardAttachment(formData).subscribe(
          result => {
            this.documents.push(result);
            this.fileUploading = false;

          }
        );
      };
    }

  }

  remove(tag: Tag): void {
    const index = this.tags.indexOf(tag);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

  removeGroup(val: any): void {
    const index = this.selectedPeopleAndGroupArray.indexOf(val);

    if (index >= 0) {
      this.selectedPeopleAndGroupArray.splice(index, 1);
    }
  }

  createBulletinPost() {
    this.bulletinBoardPost.department = this.currentDept;
    this.bulletinBoardPost.attachments = this.documents;
    this.bulletinBoardPost.hotTopicYn = this.hotTopicPost ? 'Y' : 'N';
    this.api.createBulletinPost(this.bulletinBoardPost).subscribe(data => {
this.posts = data;
   this.updatePosts.emit(null);

    });

  }
  confirmAddPost() {
    this.snotifyService.confirm('Are you sure you want to add post?', {
      timeout: 5000,
      showProgressBar: true,
      closeOnClick: true,
      pauseOnHover: true,
      position: SnotifyPosition.centerCenter,
      buttons: [
        { text: 'Yes', action: () =>
          this.createBulletinPost(),
         bold: false },
        { text: 'No', action: () => console.log('Clicked: No') },
      ]
    });
  }

  displayGroup() {
    this.showGroups = this.showGroups ? false : true;
    if (this.showGroups) {
      this.showPeople = false;
    }
  }

  displayPeople() {
    this.showPeople = this.showPeople ? false : true;
    if (this.showPeople) {
      this.showGroups = false;

    }

  }

  isClicked(val: any) {
    // console.log(val);
    this.selectedValue = val;
    // console.log(this.test);
    // console.log(this.selectedPeopleAndGroupArray.push(this.selectedValue));
    return this.selectedPeopleAndGroupArray.length;
  }

  sendPost(val: any[]) {
    this.showgroupAndPeoplePosts = true;
    this.selectedValue = val;
    this.groupAndPeoplePosts.push(this.selectedValue);
    // console.log(this.groupAndPeoplePosts);
    return this.groupAndPeoplePosts;
  }

}
