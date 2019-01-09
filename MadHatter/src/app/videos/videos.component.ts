import { Component, OnInit } from '@angular/core';
import { VideoService } from 'src/app/services/video.service';
import { Videos } from 'src/app/models/Videos';
// import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-videos',
  templateUrl: './videos.component.html',
  styleUrls: ['./videos.component.scss']
})
export class VideosComponent implements OnInit {

  video: Videos[] = [];
  results: any;

  constructor(
    private api: VideoService,
    // private sanatize: DomSanitizer,
  ) { }

  ngOnInit() {
    this.getVideoLinks();
  }

  getVideoLinks() {
    this.api.getVideos().subscribe(result => {
      this.video = result as any[];
      console.log(this.video);
    });
  }

  // sanatizeLinks(link) {
  //   this.sanatize.bypassSecurityTrustResourceUrl(link);
  //   return link;
  // }
}
