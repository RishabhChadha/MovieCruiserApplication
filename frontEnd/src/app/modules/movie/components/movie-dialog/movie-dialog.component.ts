import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MovieRouterModule } from '../../movie.router';
import { MovieService } from '../../movie.service';
import { Movie } from '../movie.model';



@Component({
  selector: 'movie-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {

  movie: Movie ;
  comments: string;
  actionType: string;


  constructor(public snackBar: MatSnackBar, public dialogRef: MatDialogRef<MovieDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private movieService:MovieService) {

      this.comments=data.obj.movieComments;
      this.movie=data.obj;
      this.actionType=data.actionType;
     }

  ngOnInit() {
    console.log(this.data)
  }
  onNoClick(){
    this.dialogRef.close();
  }

  updateComments(){
    console.log("comments", this.comments);
    this.movie.comments=this.comments;
    this.dialogRef.close();
    this.movieService.updateComments(this.movie).subscribe(movie =>{
      this.snackBar.open("Movie Updated successfully","",{
        duration: 2000
      });
    });
  }


}
