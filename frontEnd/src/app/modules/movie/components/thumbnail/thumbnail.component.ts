import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from '../movie.model';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';



@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  movie: Movie;

  @Input()
  usewatchListApi:boolean;

  @Output()
  addMovie=new EventEmitter();

  @Output()
  deleteMovie= new EventEmitter();

  @Output()
  updateMovie= new EventEmitter();

 

  constructor( private snackBar:MatSnackBar, private dialog: MatDialog) {
   
  }
 
  ngOnInit() {
   
  }

  addToWathList(){

    this.addMovie.emit(this.movie);
  
  }

  deleteFromWatchList(){
    console.log("delete in thumbnail");
    this.deleteMovie.emit(this.movie);
  
  }

  updateFromWatchList(actionType){
    console.log("movie is getting updated");
    let dialogRef= this.dialog.open(MovieDialogComponent, {
      width: '400px',
      data: {obj:this.movie, actionType:actionType}
    });
    console.log("open dialog");
    dialogRef.afterClosed().subscribe(result =>{
      console.log('the dialog is closed');
    });

  }
}
