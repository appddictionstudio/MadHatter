import { SearchService } from './search/search.service';
import { ActivityComponent } from './newsfeed/activity/activity.component';
import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { BulletinBoardService } from '../services/bulletin-board.service';
import { BulletinMessageCenter } from '../models/bulletinBoard';
import { UserService } from '../services/user.service';
import { BulletinBoardPostComment } from '../models/BulletinBoardPostComment';
import { BulletinBoardFavorite } from '../models/BulletinBoardFavorite';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { RefCodeService } from '../services/ref-code.service';
import { RefCode } from '../models/RefCode';
import { MatChipInputEvent, PageEvent } from '@angular/material';

// import * as $ from 'jquery';
import { Tag } from '../models/Tag';
// import { url } from 'inspector';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Users } from '../models/Users';


@Component({
  selector: 'app-bulletin-board',
  templateUrl: './bulletin-board.component.html',
  styleUrls: ['./bulletin-board.component.scss'],
  providers: [NgbCarouselConfig, ActivityComponent]
})

export class BulletinBoardComponent implements OnInit, OnChanges {
  posts: BulletinMessageCenter[] = [];
  hotTopicPosts: BulletinMessageCenter[] = [];
  // bltCategoryList: any[] = [];
  newPost: string;
  newComment: string;
  arr: any[];
  activities: any[];
  test: any;
  currentUser: Users;
  loggedInUser: number;
  userArray: any[];
  url = '';

  showFav = true;
  showActivity = false;

  selectedValue;
  selectedGroupArray: any[] = [];
  selectedPeopleArray: any[] = [];

  selectedPeopleAndGroupArray: any[] = [];
  showGroups = false;
  showPeople = false;
  groupAndPeoplePosts: any[] = [[]];
  showgroupAndPeoplePosts = false;
  peopleArray = [
    'Carlo Enrique Cappellini',
    'Kelly Rogelio Villalobos',
    'Lamar Larence Lofton',
    'Noah Anita DelaCruz',
    'Aaron Alonso Trevino',
    'David Eugenio Myers',
    'Chris Panfilo Calderon'
  ];

  postOpt = [
    'Posts',
    'Topics',
    'Authors'
  ];


  groupsArray = [
    'Unit',
    'Command',
    'AirForce',
    'Marines',
    'Navy'
  ];

   text: any;
topic: any;

  favorite: BulletinBoardFavorite = new BulletinBoardFavorite();
  favorites: BulletinBoardFavorite[] = [];
  allFavorites: BulletinBoardFavorite[] = [];
  favoritePostsIds: number[] = [];
  showComments: any;
  selectedPost = '';
  bulletinBoardPost: BulletinMessageCenter = new BulletinMessageCenter();
  bulletinComment: BulletinBoardPostComment = new BulletinBoardPostComment();
  currentDept: any;

  postForm: FormGroup;
  hotTopicResults = {
    number: 0,
    length: 0,
    pageSize: 0,
    totalPages: 0,
  };
  currentPage = 0;

  lockPost = false;
  lockClick = false;

  hotTopicPost = false;
  isFavorite = false;
  showPostSection = true;
  showMessagesSection = false;
  tagsOpen = false;


  hotTopicClick = false;
  likeClick: any[];
  commentClick = false;

  fileUploading = false;
  documents: any[] = [];
  urls = new Array<string>();
  myImages = ['../../assets/imgs/Hot Topic Img/EOD_1.jpg',
    '../../assets/imgs/Hot Topic Img/EOD_2.jpg',
    '../../assets/imgs/Hot Topic Img/EOD_3.jpg',
    '../../assets/imgs/Hot Topic Img/EOD_4.jpg',
    '../../assets/imgs/Hot Topic Img/EOD_5.jpg',
    '../../assets/imgs/Hot Topic Img/EOD_6.jpg'];
  randomImg: string;

  refresh = false;

  // @Input() bulletinBoardData: BulletinMessageCenter;
  tagName: string;
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  // readonly separatorKeysCodes: number[] = [ENTER, COMMA];

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

  constructor(
    private api: BulletinBoardService,
    private searchApi: SearchService,
    private refApi: RefCodeService,
    private userService: UserService,
    private snotifyService: SnotifyService,
    private activity: ActivityComponent,
    private fb: FormBuilder,
    config: NgbCarouselConfig
  ) {
    // this.userService.getUser().subscribe(user => {
    //   this.currentDept = user.department;
    // });


    config.interval = 500000000;
    config.wrap = true;
    config.keyboard = false;
    config.pauseOnHover = true;
    config.showNavigationArrows = true;
    config.showNavigationIndicators = true;

  }

  ngOnInit() {
    this.selectedValue = '';
    // this.groupAndPeoplePosts = [this.selectedValue];

    this.api.getAllFavorites().subscribe(response => {
      this.allFavorites = response;
      this.favoritePostsIds = this.allFavorites.map(fav => fav.post ? fav.post.id : 0);
    });

    this.postForm = this.fb.group({
      postControl: ['Post']
   });

    this.loadHotTopicPosts(this.currentPage);
    this.loadBulletinPosts();
    this.getAllActivity();
    this.bulletinBoardPost.topic = '';
    this.bulletinBoardPost.text = '';
    this.bulletinBoardPost.attachments.length = 0;

    this.userService.getUser().subscribe(data => {
      this.currentUser = data;
      this.userArray = [this.currentUser];
    });
  }

  ngOnChanges() {
    this.loadBulletinPosts();
    this.getAllActivity();


  }

  setHotTopic() {
    if (this.hotTopicPost) {
      this.hotTopicPost = false;
    } else {
      this.hotTopicPost = true;
    }
  }

  setLock() {
    if (this.lockPost) {
      this.lockPost = false;
    } else {
      this.lockPost = true;
    }
    console.log(this.lockPost);
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
    console.log(val);
    this.selectedValue = val;
    // console.log(this.test);
    console.log(this.selectedPeopleAndGroupArray.push(this.selectedValue));
    return this.selectedPeopleAndGroupArray.length;
  }

  sendPost(val: any[]) {
    this.showgroupAndPeoplePosts = true;
    this.selectedValue = val;
    this.groupAndPeoplePosts.push(this.selectedValue);
    console.log(this.groupAndPeoplePosts);
    return this.groupAndPeoplePosts;
  }

  openTags() {
    this.tagsOpen = !this.tagsOpen;
  }

  postSection() {
    this.showPostSection = true;
    this.showMessagesSection = false;
  }

  messagesSection() {
    this.showPostSection = false;
    this.showMessagesSection = true;
  }

  addPost() {
    const post = new BulletinMessageCenter();
    post.text = this.newPost;

    this.posts.push(post);
    this.newPost = '';
  }
  confirmAddPost() {
    this.snotifyService.confirm('Are you sure you want to add post?', {
      timeout: 5000,
      showProgressBar: true,
      closeOnClick: true,
      pauseOnHover: true,
      position: SnotifyPosition.centerBottom,
      buttons: [
        { text: 'Yes', action: () => this.createBulletinPost(), bold: false },
        { text: 'No', action: () => console.log('Clicked: No') },
      ]
    });
  }
  // addComment(post: BulletinMessageCenter) {
  //   const comment = new BulletinBoardPostComment();
  //   comment.text = this.newComment;

  //   if (!post.comments) {
  //     post.comments = [];
  //   }
  //   post.comments.push(comment);

  //   this.newComment = '';
  // }

  hotTopicClickEvent() {
    console.log('clickededededed');
    this.hotTopicClick = !this.hotTopicClick;

  }

  likeClickEvent(post) {
    if (post.liked) {
      post.liked = false;
    } else {
      post.liked = true;
    }

    const postId = post.id;
       this.api.createBulletinPostLike(postId).subscribe(data => {
         if (data) {
           this.ngOnChanges();
           post.liked = true;
         } else {
           this.ngOnChanges();
           post.liked = false;
         }
    });
    // if (this.likeClick !== id) {
    //   this.likeClick = id;
    // } else {
    //   this.likeClick = [];
    // }
    // add like to post database //
  }

  // addToFavs(post: BulletinMessageCenter) {
  //   post.isFavorited = true;

  //   const fav = new BulletinBoardFavorite();
  //   fav.post = post;

  //   this.allFavorites.push(fav);
  // }

  commentSection(id) {
    this.newComment = '';
    if (this.showComments !== id) {
      this.showComments = id;
    } else {
      this.showComments = '';
    }

  }

  createPostComment(postId, index) {
    // this.bulletinComment.department = this.currentDept;
    this.api.createPostComment(this.bulletinComment, postId).subscribe(data => {
      this.posts[index] = data;
    });
    this.bulletinComment.text = '';
  }

    createBulletinPost() {
    // this.bulletinBoardPost.department = this.currentDept;
    this.bulletinBoardPost.attachments = this.documents;
    this.bulletinBoardPost.hotTopicYn = this.hotTopicPost ? 'Y' : 'N';
    this.bulletinBoardPost.lockYn = this.lockPost ? 'Y' : 'N';
    this.api.createBulletinPost(this.bulletinBoardPost).subscribe(data => {
      this.ngOnInit();
    });

  }



  loadBulletinPosts() {
   const id = this.loggedInUser = 44206;
    this.api.getAllBulletinPost().subscribe(data => {
      this.posts = data as any[];
      this.api.getPostlikesByUserId(id).subscribe(likes => {
        for ( const like of likes) {
          for (const post of this.posts) {
            if (post.id === like.post.id && like.eodTech.id === id) {
              console.log(post.id + 'is liked');
              post.liked = true;
              post.likeCount = post.likeCount;
            }
          }
        }
      });
      // for (const post of this.posts) {
      //   this.api.getPostlikesByEodTechId(id).subscribe(likes => {
      //     for (const like of likes) {
      //       console.log(like);
      //       console.log(like.eodTech.id);
      //       if (like.id !== null && like.eodTech.id === id) {
      //         console.log(like.eodTech.id);
      //         post.liked = true;
      //       } else {
      //         post.liked = false;
      //       }
      //     }
      //   });
      // }
    });
  }


  loadHotTopicPosts(currentPage: number) {

    this.api.getHotTopicPosts(currentPage).subscribe(data => {
      const res = data as any;



      if (res) {


        this.hotTopicResults.number = res.number;
        this.hotTopicResults.length = res.totalElements;
        this.hotTopicResults.pageSize = res.size;
        this.hotTopicResults.totalPages = res.totalPages;
        this.hotTopicPosts = res.content as any[];
      }
      this.hotTopicPosts.forEach(post => {
        post.image = this.getRandomImage();
      });
    });
  }

  pageEventHandler(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.loadHotTopicPosts(this.currentPage);

    console.log(this.currentPage);
  }

  searchBulletinPosts(searchText) {
    this.searchApi.searchBulletinPost(searchText).subscribe(data => {
      this.posts = data as any[];
    });
  }

  // searchBulletinTopics(searchTopic) {
  //   this.searchApi.searchBulletinTopic(searchTopic).subscribe(data => {
  //     this.hotTopicPosts = data as any[];
  //   });
  // }

  // loadCategoryList(){
  //   this.refApi.getBltCategory().subscribe(data =>{
  //     this.bltCategoryList = data as any[];
  //   })
  // }


  getAllActivity() {
    // this.bulletinBoardPost.department = this.currentDept;
    this.api.getAllActivity().subscribe(data => {
      this.activities = data;
    });
  }

  // hardcoded images for Hot topic images
  getRandomImage() {
    this.randomImg = this.myImages[Math.floor(Math.random() * this.myImages.length)];
    console.log(this.randomImg);
    return this.randomImg;
  }
  // searchBulletinPostAndTopics(text?: any, topic?: any) {


  //  if (text !== null) {
  //   this.text = text ;
  //    this.searchApi.searchBulletinPost(this.text).subscribe(data => {
  //     this.posts = data as any[];
  //    });
  //   }
  //  if ( topic !== null) {
  //    this.topic = topic;
  //   this.searchApi.searchBulletinTopic(this.topic).subscribe(data => {
  //     this.posts = data as any[];
  //  });
  // }
  //  if (topic !== null && text !== null) {
  //   this.topic = topic;
  //   this.text = text ;
  //   this.searchApi.searchBulletinPostAndTopics(this.text, this.topic).subscribe(data => {
  //     this.posts = data as any[];
  //   });
  //  }
  // }





  onFileChange(event) {
    console.log(event);
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


  addToFavs(post: BulletinMessageCenter) {
    console.log(post);
    post.isFavorited = true;
    this.favorite.post = post;
    this.favorite.created = new Date;
    this.favorite.eodTech = post.author;
    this.api.postFavorite(this.favorite).subscribe(res => {
      // this.allFavorites = res;
      this.allFavorites.push(this.favorite);
      this.ngOnInit();
    });
  }

  toggleRightPanel(selection) {
    if (selection === 'fav') {
      this.showFav = true;
      this.showActivity = false;
    } else if (selection === 'activity') {
      this.showFav = false;
      this.showActivity = true;
    }
  }



}
