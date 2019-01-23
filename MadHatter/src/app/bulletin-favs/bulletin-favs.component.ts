import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BulletinBoardService } from '../services/bulletin-board.service';

@Component({
  selector: 'app-bulletin-favs',
  templateUrl: './bulletin-favs.component.html',
  styleUrls: ['./bulletin-favs.component.scss']
})
export class BulletinFavsComponent implements OnInit {

  constructor(private bulletinService: BulletinBoardService) { }

  @Input() allFavorites: BulletinFavsComponent[];
  @Output() updateFav: EventEmitter<any> = new EventEmitter();


  deleteFav(nu: number) {


    this.bulletinService.deleteFavorite(nu).subscribe(() => {
      console.log('DELETED');
      this.bulletinService.getAllFavorites().subscribe(data => {
        this.allFavorites = data;
        this.updateFav.emit(null);
      });
    });

  }
  ngOnInit() {


    // this.bulletinService.getAllFavorites().subscribe(response => {
    //   this.allFavorites = response;
    //   console.log(this.allFavorites);

    // });
  }

}
