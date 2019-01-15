import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { VideoService } from 'src/app/services/video.service';
import { Videos } from 'src/app/models/Videos';
import {DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-videos',
  templateUrl: './videos.component.html',
  styleUrls: ['./videos.component.scss']
})


export class VideosComponent implements OnInit {

  video: Videos[] = [];
  results: any;
  url: any;
  videoUrl: any;
  name: any;


  constructor(
    private api: VideoService,
    private sanatize: DomSanitizer,
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

  sanatizeName(name) {
    this.name = this.sanatize.bypassSecurityTrustHtml(name);
    return name;
  }
}
