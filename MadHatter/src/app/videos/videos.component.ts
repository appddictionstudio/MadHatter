import { Component, OnInit } from '@angular/core';
import { VideoService } from 'src/app/services/video.service';
import { Videos } from 'src/app/models/Videos';

@Component({
  selector: 'app-videos',
  templateUrl: './videos.component.html',
  styleUrls: ['./videos.component.scss']
})
export class VideosComponent implements OnInit {

  video: Videos[];

  constructor(
    private vidService: VideoService,
  ) { }

  ngOnInit() {
    console.log('about to do something');
    this.vidService.getVideos().subscribe(result => {
      console.log('did something');
      const res = result as any;
      console.log(res);
      if (res) {
        this.video = res;
      }
    });
  }

}
