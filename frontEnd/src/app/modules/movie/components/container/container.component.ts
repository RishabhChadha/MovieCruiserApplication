import { Component, OnInit, Input } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../movie.model';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
 
 
  @Input()
  movies: Array<Movie>;

  @Input()
  usewatchListApi: boolean;


  movieType;


  constructor(private movieService: MovieService,private matSnackBar:MatSnackBar){

  }

  ngOnInit() {
 console.log(this.usewatchListApi+"in container")
  }


  addMovieToWatchList(movie){
    
    let message= `$(movie.title) add to watch list`;
    console.log(movie.title+"title")
    this.movieService.saveWatchListMovies(movie).subscribe((movie) => {
      this.matSnackBar.open('Movie added to the watch list','',{
        duration:1000
      })
    } );
  }

    deleteFromWatchList(movie){
      console.log("114")
      let message= `${movie.title} add to watch list`;
      for(var i=0;i<this.movies.length;i++){
        if(this.movies[i].title === movie.title){
          this.movies.splice(i,1);
        }
      }
      this.movieService.deleteFromWatchList(movie).subscribe((movie) =>{
        this.matSnackBar.open(message, '', {
          duration:1000
        });
      });
    }

  }

